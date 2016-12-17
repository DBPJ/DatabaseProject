import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.AbstractTableModel;

public class TableEditorSample {

    public static void main(String[] args) {
        new TableEditorSample();
    }

    public TableEditorSample() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                ShoppingListModel model = new ShoppingListModel();
                model.addEntry(new ShoppingEntry(0, null));
                model.addEntry(new ShoppingEntry(0, null));
                JTable table = new JTable(model);

                JComboBox cb = new JComboBox(new String[]{
                        "Apples",
                        "Bananas",
                        "Pears",
                        "Ice-Cream",
                        "World Domination",
                });
                table.
                        getColumnModel().
                        getColumn(1).
                        setCellEditor(new DefaultCellEditor(cb));

                JFrame frame = new JFrame("Testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(new JScrollPane(table));
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class ShoppingListModel extends AbstractTableModel {

        private List<ShoppingEntry> shoppingEntries;

        public ShoppingListModel() {
            shoppingEntries = new ArrayList<ShoppingEntry>(25);
        }

        public void addEntry(ShoppingEntry entry) {
            shoppingEntries.add(entry);
            fireTableRowsInserted(shoppingEntries.size() - 1, shoppingEntries.size() - 1);
        }

        @Override
        public int getRowCount() {
            return shoppingEntries.size();
        }

        @Override
        public String getColumnName(int column) {
            return column == 0 ? "Qty" : "Item";
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return columnIndex == 0 ? Integer.class : String.class;
        }

        @Override
        public int getColumnCount() {
            return 2;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Object value = null;
            switch (columnIndex) {
                case 0:
                    value = shoppingEntries.get(rowIndex).getQty();
                    break;
                case 1:
                    value = shoppingEntries.get(rowIndex).getItem();
                    break;
            }
            return value;
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return true;
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            ShoppingEntry entry = shoppingEntries.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    int qty = 0;
                    if (aValue instanceof String) {
                        qty = Integer.parseInt(aValue.toString());
                    } else if (aValue instanceof Integer) {
                        qty = (Integer)aValue;
                    }
                    entry.setQty(qty);
                    break;
                case 1:
                    entry.setItem(aValue == null ? null : aValue.toString());
                    break;
            }
            fireTableCellUpdated(rowIndex, columnIndex);
        }

    }

    public class ShoppingEntry {

        private int qty;
        private String item;

        public ShoppingEntry(int qty, String item) {
            this.qty = qty;
            this.item = item;
        }

        public String getItem() {
            return item;
        }

        public int getQty() {
            return qty;
        }

        public void setItem(String item) {
            this.item = item;
        }

        public void setQty(int qty) {
            this.qty = qty;
        }

    }

}