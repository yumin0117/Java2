package book_management.view;

import book_management.domain.Book;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.ArrayList;

public class BookInsertPan extends JPanel {
    Book book;
    JTable table;
    DefaultTableModel model;
    ArrayList<Book> bookList;
    String[] tableHeader = {"도서번호", "도서명", "저자명",  "출판사", "가격", "재고량", "카테고리"};
    String[] header = {"도서번호", "도서명", "저자명", "가격", "재고량", "출판사", "카테고리"};
    String[] categoryNames = {"IT도서", "소설", "수필", "시"};
    String[] publishNames = {"한빛아카데미", "생능출판사", "민음사", "해냄출판사"};
    JLabel[] lbls = new JLabel[tableHeader.length];
    JTextField[] tfs = new JTextField[tableHeader.length - 2];
    JComboBox<String> comboCategory, comboPublish;
    JButton btnInsert = new JButton("도서추가");
    JPanel panS = new JPanel(new GridLayout(4, 4, 5, 2));

    public BookInsertPan() {
        setLayout(new BorderLayout());

        comboCategory = new JComboBox<String>(categoryNames);
        comboPublish = new JComboBox<String>(publishNames);

        for (int i = 0; i < header.length; i++) {
            lbls[i] = new JLabel(header[i]);
            panS.add(lbls[i]);
            if (i < header.length - 2) {
                tfs[i] = new JTextField();
                panS.add(tfs[i]);
            }
            else if(i == 5){
                panS.add(comboPublish);
            }else {
                panS.add(comboCategory);
            }
        }

        JLabel emptyLbl = new JLabel(" ");
        panS.add(emptyLbl);
        panS.add(btnInsert);
    }

    public void initView(){
        model = new DefaultTableModel(tableHeader, bookList.size()){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(model);
        TableColumnModel tcm = table.getColumnModel();

        tcm.getColumn(0).setPreferredWidth(75);
        tcm.getColumn(1).setPreferredWidth(200);
        tcm.getColumn(2).setPreferredWidth(150);
        tcm.getColumn(3).setPreferredWidth(100);
        tcm.getColumn(4).setPreferredWidth(75);
        tcm.getColumn(5).setPreferredWidth(100);

        JScrollPane scroll = new JScrollPane(table);
        putSearchResult();

        add("Center", scroll);
        add("South", panS);
    }

    public void putSearchResult(){
        model.setRowCount(bookList.size());
        Book book = null;

        for (int i = 0; i < bookList.size(); i++){
            book = bookList.get(i);
            model.setValueAt(book.getIsbn(),  i, 0);
            model.setValueAt(book.getName(),  i, 1);
            model.setValueAt(book.getAuthor(),  i, 2);
            model.setValueAt(book.getPublish_name(),  i, 3);
            model.setValueAt(book.getPrice(),  i, 4);
            model.setValueAt(book.getInventory(),  i, 5);
            model.setValueAt(book.getCategory_name(),  i, 6);
        }
    }

    public ArrayList<Book> getBookList() {
        return bookList;
    }

    public void setBookList(ArrayList<Book> bookList) {
        this.bookList = bookList;
    }

    public Book getBook() {
        book = new Book();
        book.setIsbn(tfs[0].getText());
        book.setName(tfs[1].getText());
        book.setAuthor(tfs[2].getText());
        book.setPrice(Integer.parseInt(tfs[3].getText()));
        book.setInventory(Integer.parseInt(tfs[4].getText()));
        book.setPublish_name((String) comboPublish.getSelectedItem());
        book.setCategory_name((String) comboCategory.getSelectedItem());
        return book;
    }

    public JButton getBtnInsert() {
        return btnInsert;
    }
}
