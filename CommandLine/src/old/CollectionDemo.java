package old;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.*;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

/**
 * A Swing-based application hosting three demos of some of the
 * interfaces from the Java Collections Framework.  The application
 * consists of a main frame containing a tabbed pane for selecting
 * between a Set demo, a List demo, a Queue demo, and a Map demo.
 * Each of these demos are panels described separately.
 */
@SuppressWarnings("serial")
public class CollectionDemo extends JPanel {

    protected JPanel buttonPanel = new JPanel();
    protected JTextArea display = new JTextArea(10, 30);
    protected JLabel message = new JLabel(" ");
    Map m = new HashMap();

    /**
     * Constructs the common part of each demo panel.
     */
    protected CollectionDemo() {
        setLayout(new BorderLayout());
        display.setEditable(false);
        add(new JScrollPane(display), BorderLayout.CENTER);
        message.setBackground(Color.lightGray);
        add(message, BorderLayout.SOUTH);
        buttonPanel.setLayout(new GridLayout(0, 1));
        JPanel buttonPanelWrapper = new JPanel();
        buttonPanelWrapper.add(buttonPanel);
        add(buttonPanelWrapper, BorderLayout.EAST);
    }

    /**
     * Returns a list of the non-space substrings of a string.
     */
    protected static Collection<String> tokensOf(String input) {
        return Arrays.asList(input.split("\\s+"));
    }

    /**
     * Runs the application.
     */
    public static void main(String[] args) {
        JTabbedPane pane = new JTabbedPane();
        pane.addTab("Set", null, new SetDemo(), "Demo sets");
        pane.addTab("List", null, new ListDemo(), "Demo lists");
        pane.addTab("Queue", null, new QueueDemo(), "Demo queues");
        pane.addTab("Map", null, new MapDemo(), "Demo maps");

        JFrame frame = new JFrame("Collection Demo");
        frame.getContentPane().add(pane, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

/**
 * A panel with (1) a textarea that displays the contents of a field
 * which is a set, (2) a panel with buttons that invoke methods on the
 * set and cause the display to be updated if necessary, (3) a textfield
 * in which to supply arguments to the methods invoked by the buttons,
 * and (4) a message area in which responses to these methods may be
 * displayed.
 */
class SetDemo extends CollectionDemo {

    Set<String> theSet = new TreeSet<String>();
    JTextField input = new JTextField(20);

    // We will have a lot of buttons that each run a test.  We have a command
    // object which encapsulates a button and its listener.   he idea is that
    // every time you make a command, it creates a button and puts it in a panel
    // and attaches a listener which first reads the inputs, then calls a
    // button specific method with those inputs, then displays the resulting
    // set in the display area.  If an exception is thrown, the exception will
    // be written to the message area.

    private abstract class Command {
        public Command(String label) {
            buttonPanel.add(new JButton(new AbstractAction(label) {
                public void actionPerformed(ActionEvent event) {
                    try {
                        String item = input.getText();
                        message.setText(" ");
                        test(item);
                        display.setText("");
                        for (String s : theSet) {
                            display.append(s + "\n");
                        }
                    } catch (Exception e) {
                        message.setText(e.toString());
                    }
                }
            }));
        }
        protected abstract void test(String item);
    }

    /**
     * Do layout and other initialization, like coloring some components and
     * making the display area non-editable. It also creates all the "commands."
     */
    public SetDemo() {
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Item(s)"));
        inputPanel.add(input);
        add(inputPanel, "North");

        new Command("Add") {
            protected void test(String item) {
                theSet.add(item);}};
        new Command("Add All") {
            protected void test(String item) {
                theSet.addAll(tokensOf(item));}};
        new Command("Remove") {
            protected void test(String item) {
                theSet.remove(item);}};
        new Command("Remove All") {
            protected void test(String item) {
                theSet.removeAll(tokensOf(item));}};
        new Command("Retain All") {
            protected void test(String item) {
                theSet.retainAll(tokensOf(item));}};
        new Command("Contains") {
            protected void test(String item) {
                message.setText(theSet.contains(item) + "");}};
        new Command("Contains All") {
            protected void test(String item) {
                message.setText(theSet.containsAll(tokensOf(item)) + "");}};
        new Command("Size") {
            protected void test(String item) {
                message.setText(theSet.size() + " item(s) in the set");}};
        new Command("Clear") {
            protected void test(String item) {
                theSet.clear();}};
    }
}

/**
 * A panel with (1) a textarea that displays the contents of a field
 * which is a queue, (2) a panel with buttons that invoke methods on the
 * queue and cause the display to be updated if necessary, (3) a textfield
 * in which to supply arguments to the methods invoked by the buttons,
 * and (4) a message area in which responses to these methods may be
 * displayed.
 */
@SuppressWarnings("serial")
class QueueDemo extends CollectionDemo {

    Queue<String> theQueue = new LinkedList<String>();
    JTextField input = new JTextField(20);

    // We will have a lot of buttons that each run a test.  We have a command
    // object which encapsulates a button and its listener.   he idea is that
    // every time you make a command, it creates a button and puts it in a panel
    // and attaches a listener which first reads the inputs, then calls a
    // button specific method with those inputs, then displays the resulting
    // set in the display area.  If an exception is thrown, the exception will
    // be written to the message area.

    private abstract class Command {
        public Command(String label) {
            buttonPanel.add(new JButton(new AbstractAction(label) {
                public void actionPerformed(ActionEvent event) {
                    try {
                        String item = input.getText();
                        message.setText(" ");
                        test(item);
                        display.setText("");
                        for (String s : theQueue) {
                            display.append(s + "\n");
                        }
                    } catch (Exception e) {
                        message.setText(e.toString());
                    }
                }
            }));
        }
        protected abstract void test(String item);
    }

    /**
     * Do layout and other initialization, like coloring some components and
     * making the display area non-editable. It also creates all the "commands."
     */
    public QueueDemo() {
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Item(s)"));
        inputPanel.add(input);
        add(inputPanel, "North");

        new Command("Add") {
            protected void test(String item) {
                theQueue.add(item);}};
        new Command("Add All") {
            protected void test(String item) {
                theQueue.addAll(tokensOf(item));}};
        new Command("Offer") {
            protected void test(String item) {
                theQueue.offer(item);}};
        new Command("Poll") {
            protected void test(String item) {
                message.setText(theQueue.poll() + " returned");}};
        new Command("Remove()") {
            protected void test(String item) {
                message.setText(theQueue.remove() + " returned");}};
        new Command("Peek") {
            protected void test(String item) {
                message.setText(theQueue.peek() + " returned");}};
        new Command("Element") {
            protected void test(String item) {
                message.setText(theQueue.element() + " returned");}};
        new Command("Remove(x)") {
            protected void test(String item) {
                theQueue.remove(item);}};
        new Command("Remove All") {
            protected void test(String item) {
                theQueue.removeAll(tokensOf(item));}};
        new Command("Retain All") {
            protected void test(String item) {
                theQueue.retainAll(tokensOf(item));}};
        new Command("Contains") {
            protected void test(String item) {
                message.setText(theQueue.contains(item) + "");}};
        new Command("Contains All") {
            protected void test(String item) {
                message.setText(theQueue.containsAll(tokensOf(item)) + "");}};
        new Command("Size") {
            protected void test(String item) {
                message.setText(theQueue.size() + " item(s) in the queue");}};
        new Command("Clear") {
            protected void test(String item) {
                theQueue.clear();}};
    }
}

/**
 * A panel with (1) a textarea that displays the contents of a field
 * which is a list, (2) a panel with buttons that invoke methods on the
 * list and cause the display to be updated if necessary, (3) textfields
 * in which to supply arguments to the methods invoked by the buttons,
 * and (4) a message area in which responses may be displayed.
 */
class ListDemo extends CollectionDemo {

    List<String> theList = new ArrayList<String>();
    JTextField indexInput = new NumericTextField(5);
    JTextField itemInput = new JTextField(20);

    // We will have a lot of buttons that each run a test.  We have a command
    // object which encapsulates a button and its listener.  The idea is that
    // every time you make a command, it creates a button and puts it in a panel
    // and attaches a listener which first reads the inputs, then calls a
    // button specific method with those inputs, then displays the resulting
    // list in the display area.  If an exception is thrown, the exception will
    // be written to the message area.

    private abstract class Command {
        public Command(String label) {
            buttonPanel.add(new JButton(new AbstractAction(label) {
                public void actionPerformed(ActionEvent event) {
                    try {
                        String index = indexInput.getText();
                        String item = itemInput.getText();
                        message.setText(" ");
                        test(index, item);
                        display.setText("");
                        for (int i = 0; i < theList.size(); i++) {
                            display.append("[" + i + "]    "
                                    + theList.get(i) + "\n");
                        }
                    } catch (Exception e) {
                        message.setText(e.toString());
                    }
                }
            }));
        }
        protected abstract void test(String index, String item);
    }

    /**
     * Do layout and other initialization, like coloring some
     * components and making the display area non-editable.    It
     * also creates all the "testers."
     */
    public ListDemo() {
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Index"));
        inputPanel.add(indexInput);
        inputPanel.add(new JLabel("Item(s)"));
        inputPanel.add(itemInput);
        add(inputPanel, BorderLayout.NORTH);

        new Command("Add") {
            protected void test(String index, String item) {
                theList.add(item);}};
        new Command("Add at Index") {
            protected void test(String index, String item) {
                theList.add(Integer.parseInt(index), item);}};
        new Command("Add All") {
            protected void test(String index, String item) {
                theList.addAll(tokensOf(item));}};
        new Command("Set") {
            protected void test(String index, String item) {
                theList.set(Integer.parseInt(index), item);}};
        new Command("Get") {
            protected void test(String index, String item) {
                message.setText(theList.get(Integer.parseInt(index)));}};
        new Command("IndexOf") {
            protected void test(String index, String item) {
                message.setText(theList.indexOf(item) + "");}};
        new Command("Remove") {
            protected void test(String index, String item) {
                theList.remove(item);}};
        new Command("Remove All") {
            protected void test(String index, String item) {
                theList.removeAll(tokensOf(item));}};
        new Command("Retain All") {
            protected void test(String index, String item) {
                theList.retainAll(tokensOf(item));}};
        new Command("Contains") {
            protected void test(String index, String item) {
                message.setText(theList.contains(item) + "");}};
        new Command("Contains All") {
            protected void test(String index, String item) {
                message.setText(theList.containsAll(tokensOf(item)) + "");}};
        new Command("Reverse") {
            protected void test(String index, String item) {
                Collections.reverse(theList);}};
        new Command("Fill") {
            protected void test(String index, String item) {
                Collections.fill(theList, item);}};
        new Command("Shuffle") {
            protected void test(String index, String item) {
                Collections.shuffle(theList);}};
        new Command("Sort") {
            protected void test(String index, String item) {
                Collections.sort(theList);}};
        new Command("Size") {
            protected void test(String index, String item) {
                message.setText(theList.size() + " item(s) in the list");}};
        new Command("Clear") {
            protected void test(String index, String item) {
                theList.clear();}};
    }
}

/**
 * A panel with (1) a textarea that displays the contents of a field
 * which is a map, (2) a panel with buttons that invoke methods on the
 * map and cause the display to be updated if necessary, (3) textfields
 * in which to supply arguments to the methods invoked by the buttons,
 * and (4) a message area in which responses may be displayed.
 */
@SuppressWarnings("serial")
class MapDemo extends CollectionDemo {

    Map<String, String> theMap = new TreeMap<String,String>();
    JTextField keyInput = new JTextField(10);
    JTextField valueInput = new JTextField(10);

    // We will have a lot of buttons that each run a test.  We have a command
    // object which encapsulates a button and its listener.  The idea is that
    // every time you make a command, it creates a button and puts it in a panel
    // and attaches a listener which first reads the inputs, then calls a
    // button specific method with those inputs, then displays the resulting
    // list in the display area.  If an exception is thrown, the exception will
    // be written to the message area.

    private abstract class Command {
        public Command(String label) {
            buttonPanel.add(new JButton(new AbstractAction(label) {
                public void actionPerformed(ActionEvent event) {
                    try {
                        String key = keyInput.getText();
                        String value = valueInput.getText();
                        message.setText(" ");
                        test(key, value);
                        display.setText("");
                        for (Map.Entry e : theMap.entrySet()) {
                            display.append(e + "\n");
                        }
                    } catch (Exception e) {
                        message.setText(e.toString());
                    }
                }
            }));
        }
        protected abstract void test(String key, String value);
    }

    /**
     * Do layout and other initialization, like coloring some
     * components and making the display area non-editable.    It
     * also creates all the "commands."
     */
    public MapDemo() {
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Key"));
        inputPanel.add(keyInput);
        inputPanel.add(new JLabel("Value"));
        inputPanel.add(valueInput);
        add(inputPanel, "North");

        new Command("Put") {
            protected void test(String key, String value) {
                theMap.put(key, value);}};
        new Command("Get") {
            protected void test(String key, String value) {
                String answer = theMap.get(key);
                message.setText(answer == null ? "NO_SUCH_KEY" : answer);}};
        new Command("Remove") {
            protected void test(String key, String value) {
                theMap.remove(key);}};
        new Command("Contains Key") {
            protected void test(String key, String value) {
                message.setText(theMap.containsKey(key) + "");}};
        new Command("Contains Value") {
            protected void test(String key, String value) {
                message.setText(theMap.containsValue(value) + "");}};
        new Command("Size") {
            protected void test(String key, String value) {
                message.setText(theMap.size() + " item(s) in the map");}};
        new Command("Clear") {
            protected void test(String key, String value) {
                theMap.clear();}};
    }
}

/**
 * A simple extension of JTextField whose instances only allow
 * numeric characters.  Attempts to insert non-numeric characters
 * into a old.NumericTextField quietly fail.
 */
@SuppressWarnings("serial")
class NumericTextField extends JTextField {

    public NumericTextField(int columns) {
        super(columns);
    }

    protected Document createDefaultModel() {
        return new NumericDocument();
    }

    static class NumericDocument extends PlainDocument {
        public void insertString(int offset, String string, AttributeSet a)
                throws BadLocationException {
            if (string == null) {
                return;
            }
            for (int i = 0; i < string.length(); i++) {
                if (!Character.isDigit(string.charAt(i))) {
                    return;
                }
            }
            super.insertString(offset, string, a);
        }
    }
}