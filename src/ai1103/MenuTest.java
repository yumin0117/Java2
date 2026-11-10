package ai1103;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/** 11월 3일 메뉴 실습 확장: 파일 저장, 편집, 찾기, 보기 기능. */
public final class MenuTest extends JFrame {
    private static final long serialVersionUID = 1L;
    final JTextArea textArea = new JTextArea();
    final JFileChooser fileChooser = new JFileChooser();
    private final UndoManager undoManager = new UndoManager();
    private final JLabel statusLabel = new JLabel();
    private final Color[] colorNames = {Color.WHITE, Color.BLUE, Color.MAGENTA};
    private final String[] colorLabels = {"White", "Blue", "Magenta"};
    private JMenuItem undoItem;
    private JMenuItem redoItem;
    private File currentFile;
    private String savedText = "";
    private String lastSearch = "";

    public MenuTest() {
        super("MenuTest 메모장");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(780, 560);
        setMinimumSize(new Dimension(560, 380));
        setLocationRelativeTo(null);

        textArea.setFont(new Font(Font.DIALOG, Font.PLAIN, 16));
        textArea.setMargin(new Insets(14, 14, 14, 14));
        textArea.setWrapStyleWord(true);
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(createToolBar(), BorderLayout.NORTH);
        statusLabel.setBorder(BorderFactory.createEmptyBorder(6, 12, 6, 12));
        add(statusLabel, BorderLayout.SOUTH);
        setJMenuBar(createMenuBar());
        fileChooser.setFileFilter(new FileNameExtensionFilter("텍스트 파일 (*.txt)", "txt"));

        // 문서 변경을 감지하여 제목의 별표와 상태 표시줄을 갱신한다.
        textArea.getDocument().addUndoableEditListener(e -> {
            undoManager.addEdit(e.getEdit());
            updateUndoItems();
        });
        textArea.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { updateStatus(); }
            public void removeUpdate(DocumentEvent e) { updateStatus(); }
            public void changedUpdate(DocumentEvent e) { updateStatus(); }
        });
        textArea.addCaretListener(e -> updateStatus());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) { closeDocument(); }
        });
        updateStatus();
        updateUndoItems();
        setVisible(true);
    }

    private JMenuBar createMenuBar() {
        JMenuBar bar = new JMenuBar();
        JMenu file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);
        addItem(file, "New", "control N");
        addItem(file, "Open", "control O");
        file.addSeparator();
        addItem(file, "Save", "control S");
        addItem(file, "Save As", "control shift S");
        addItem(file, "Save as temporary", null);
        file.addSeparator();
        addItem(file, "Close", "alt F4");
        bar.add(file);

        JMenu edit = new JMenu("Edit");
        edit.setMnemonic(KeyEvent.VK_E);
        undoItem = addItem(edit, "Undo", "control Z");
        redoItem = addItem(edit, "Redo", "control Y");
        edit.addSeparator();
        addItem(edit, "Cut", "control X");
        addItem(edit, "Copy", "control C");
        addItem(edit, "Paste", "control V");
        addItem(edit, "Select All", "control A");
        edit.addSeparator();
        addItem(edit, "Find", "control F");
        addItem(edit, "Find Next", "F3");
        edit.addSeparator();
        // 기존 실습의 배경색 메뉴를 유지한다.
        for (String color : colorLabels) addItem(edit, color, null);
        bar.add(edit);

        JMenu format = new JMenu("Format");
        JCheckBoxMenuItem wrap = new JCheckBoxMenuItem("Word Wrap");
        wrap.addActionListener(e -> textArea.setLineWrap(wrap.isSelected()));
        format.add(wrap);
        JMenu size = new JMenu("Font Size");
        ButtonGroup sizes = new ButtonGroup();
        for (int value : new int[]{12, 16, 20, 24, 28}) {
            JRadioButtonMenuItem item = new JRadioButtonMenuItem(value + " pt", value == 16);
            item.addActionListener(e -> textArea.setFont(textArea.getFont().deriveFont((float) value)));
            sizes.add(item);
            size.add(item);
        }
        format.add(size);
        bar.add(format);

        JMenu help = new JMenu("Help");
        addItem(help, "About", null);
        bar.add(help);
        return bar;
    }

    private JMenuItem addItem(JMenu menu, String name, String shortcut) {
        JMenuItem item = new JMenuItem(name);
        if (shortcut != null) item.setAccelerator(KeyStroke.getKeyStroke(shortcut));
        item.addActionListener(e -> performAction(name));
        menu.add(item);
        return item;
    }

    private JToolBar createToolBar() {
        JToolBar bar = new JToolBar();
        bar.setFloatable(false);
        String[] commands = {"New", "Open", "Save", "Close"};
        String[] icons = {"new", "open", "save", "close"};
        for (int i = 0; i < commands.length; i++) {
            String command = commands[i];
            File iconFile = new File("toolbar/" + icons[i] + ".jpg");
            JButton button;
            // 기존 사진을 재사용하고, 이미지가 없어도 기능을 사용할 수 있다.
            if (iconFile.isFile()) {
                Image image = new ImageIcon(iconFile.getPath()).getImage();
                button = new JButton(new ImageIcon(image.getScaledInstance(22, 22, Image.SCALE_SMOOTH)));
            } else {
                button = new JButton(command);
            }
            button.setToolTipText(command);
            button.getAccessibleContext().setAccessibleName(command);
            button.addActionListener(e -> performAction(command));
            bar.add(button);
        }
        bar.addSeparator();
        for (int i = 0; i < colorNames.length; i++) {
            String command = colorLabels[i];
            JButton button = new JButton("  ");
            button.setBackground(colorNames[i]);
            button.setOpaque(true);
            button.setToolTipText(command);
            button.getAccessibleContext().setAccessibleName(command);
            button.addActionListener(e -> performAction(command));
            bar.add(button);
        }
        return bar;
    }

    // 메뉴와 툴바가 같은 메서드를 호출하여 동작을 일치시킨다.
    private void performAction(String command) {
        switch (command) {
            case "New":
                if (confirmSaveChanges()) setDocument("", null);
                break;
            case "Open": openDocument(); break;
            case "Save": saveDocument(false); break;
            case "Save As": saveDocument(true); break;
            case "Save as temporary": saveDraftFile(textArea.getText()); break;
            case "Close": closeDocument(); break;
            case "Undo":
                if (undoManager.canUndo()) undoManager.undo();
                updateUndoItems();
                break;
            case "Redo":
                if (undoManager.canRedo()) undoManager.redo();
                updateUndoItems();
                break;
            case "Cut": textArea.cut(); break;
            case "Copy": textArea.copy(); break;
            case "Paste": textArea.paste(); break;
            case "Select All": textArea.selectAll(); break;
            case "Find": findText(true); break;
            case "Find Next": findText(false); break;
            case "White": changeBackground(Color.WHITE); break;
            case "Blue": changeBackground(Color.BLUE); break;
            case "Magenta": changeBackground(Color.MAGENTA); break;
            case "About":
                JOptionPane.showMessageDialog(this,
                        "MenuTest 메모장\nUTF-8 텍스트 저장 및 편집\n"
                                + "Ctrl+S 저장 / Ctrl+F 찾기 / F3 다음 찾기\n"
                                + "임시 저장: 실행 폴더의 temp.txt",
                        "About", JOptionPane.INFORMATION_MESSAGE);
                break;
            default: break;
        }
    }

    private void changeBackground(Color color) {
        textArea.setBackground(color);
        // 파란 배경에서도 글자와 커서가 잘 보이도록 한다.
        Color foreground = Color.BLUE.equals(color) ? Color.WHITE : Color.BLACK;
        textArea.setForeground(foreground);
        textArea.setCaretColor(foreground);
    }

    private void openDocument() {
        if (fileChooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) return;
        File selected = fileChooser.getSelectedFile();
        if (selected == null || !confirmSaveChanges()) return;
        try {
            String content = readFile(selected);
            setDocument(content, selected);
        } catch (IOException ex) {
            showFileError("파일을 열 수 없습니다.", ex);
        }
    }

    public String readFile(File file) throws IOException {
        return Files.readString(file.toPath(), StandardCharsets.UTF_8);
    }

    private void setDocument(String content, File file) {
        textArea.setText(content);
        textArea.setCaretPosition(0);
        currentFile = file;
        savedText = content;
        undoManager.discardAllEdits();
        updateUndoItems();
        updateStatus();
    }

    private boolean saveDocument(boolean saveAs) {
        File destination = currentFile;
        if (saveAs || destination == null) {
            fileChooser.setSelectedFile(destination == null ? new File("memo.txt") : destination);
            if (fileChooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) return false;
            destination = fileChooser.getSelectedFile();
            if (destination == null) return false;
            if (!destination.getName().contains(".")) {
                destination = new File(destination.getParentFile(), destination.getName() + ".txt");
            }
            if (destination.exists() && JOptionPane.showConfirmDialog(this,
                    "같은 이름의 파일이 있습니다. 덮어쓸까요?\n" + destination.getName(),
                    "덮어쓰기 확인", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) return false;
        }
        try {
            Files.writeString(destination.toPath(), textArea.getText(), StandardCharsets.UTF_8);
            currentFile = destination;
            savedText = textArea.getText();
            updateStatus();
            return true;
        } catch (IOException ex) {
            showFileError("파일을 저장할 수 없습니다.", ex);
            return false;
        }
    }

    public void saveDraftFile(String content) {
        File draft = new File("temp.txt");
        if (draft.exists() && JOptionPane.showConfirmDialog(this,
                "기존 temp.txt를 덮어쓸까요?", "임시 저장", JOptionPane.YES_NO_OPTION)
                != JOptionPane.YES_OPTION) return;
        try {
            Files.writeString(draft.toPath(), content, StandardCharsets.UTF_8);
            JOptionPane.showMessageDialog(this, "임시 저장 완료\n" + draft.getAbsolutePath());
            // 임시 저장은 정식 저장 상태를 바꾸지 않는다.
        } catch (IOException ex) {
            showFileError("임시 저장에 실패했습니다.", ex);
        }
    }

    private boolean confirmSaveChanges() {
        if (savedText.equals(textArea.getText())) return true;
        int result = JOptionPane.showConfirmDialog(this,
                "변경한 내용을 저장할까요?", "저장 확인", JOptionPane.YES_NO_CANCEL_OPTION);
        if (result == JOptionPane.YES_OPTION) return saveDocument(false);
        return result == JOptionPane.NO_OPTION;
    }

    private void closeDocument() {
        if (confirmSaveChanges()) dispose();
    }

    private void findText(boolean askKeyword) {
        if (askKeyword || lastSearch.isEmpty()) {
            String keyword = (String) JOptionPane.showInputDialog(this, "찾을 내용을 입력하세요. (대소문자 구분)",
                    "Find", JOptionPane.QUESTION_MESSAGE, null, null, lastSearch);
            if (keyword == null || keyword.isEmpty()) return;
            lastSearch = keyword;
        }
        String content = textArea.getText();
        int index = content.indexOf(lastSearch, textArea.getSelectionEnd());
        if (index < 0) index = content.indexOf(lastSearch); // 끝에 도달하면 처음부터 검색
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "찾는 내용이 없습니다.");
        } else {
            textArea.requestFocusInWindow();
            textArea.select(index, index + lastSearch.length());
        }
    }

    private void updateUndoItems() {
        undoItem.setEnabled(undoManager.canUndo());
        redoItem.setEnabled(undoManager.canRedo());
    }

    private void updateStatus() {
        String content = textArea.getText();
        boolean changed = !savedText.equals(content);
        String filename = currentFile == null ? "새 문서" : currentFile.getName();
        setTitle((changed ? "* " : "") + filename + " - MenuTest 메모장");
        int line = 1;
        int column = 1;
        try {
            int caret = textArea.getCaretPosition();
            line = textArea.getLineOfOffset(caret) + 1;
            int start = textArea.getLineStartOffset(line - 1);
            column = content.codePointCount(start, caret) + 1;
        } catch (javax.swing.text.BadLocationException ignored) {
            // 문서 갱신 중에는 기본 위치를 표시한다.
        }
        statusLabel.setText("UTF-8  |  " + content.codePointCount(0, content.length())
                + "자 (공백·줄바꿈 포함)  |  " + line + "행 " + column + "열"
                + (changed ? "  |  수정됨" : "  |  변경 없음"));
    }

    private void showFileError(String message, IOException ex) {
        JOptionPane.showMessageDialog(this, message + "\n" + ex.getMessage(),
                "파일 오류", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        // Swing 화면은 이벤트 디스패치 스레드에서 생성한다.
        SwingUtilities.invokeLater(MenuTest::new);
    }
}
