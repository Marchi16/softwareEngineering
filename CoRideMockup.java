import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

/**
 * CoRide — Mock-up Screens (Java Swing)
 * Οθόνες: Login, Εγγραφή, Dashboard, Προσφορά Διαδρομής,
 *          Αναζήτηση & Κράτηση, Πόντοι & Προνόμια
 *
 * Εκτέλεση: javac CoRideMockup.java && java CoRideMockup
 */
public class CoRideMockup {

    // ── Palette ──────────────────────────────────────────────
    static final Color BG       = new Color(0xFAFAF8);
    static final Color FG       = new Color(0x1A1A1A);
    static final Color ACCENT   = new Color(0x1A5276);   // μπλε (eco)
    static final Color ACCENT2  = new Color(0x52B788);
    static final Color SUBTLE   = new Color(0xE8F5E9);
    static final Color BORDER   = new Color(0xCCCCCC);
    static final Color MUTED    = new Color(0x777777);
    static final Font  TITLE    = new Font("SansSerif", Font.BOLD,  14);
    static final Font  BODY     = new Font("SansSerif", Font.PLAIN, 12);
    static final Font  SMALL    = new Font("SansSerif", Font.PLAIN, 10);
    static final Font  BOLD_SM  = new Font("SansSerif", Font.BOLD,  11);

    // ── Entry point ──────────────────────────────────────────
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UIManager.put("Panel.background", BG);
            showAllScreens();
        });
    }

    static void showAllScreens() {
        // Κεντρικό μενού επιλογής οθόνης
        JFrame menu = new JFrame("CoRide — Mock-up Navigator");
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setSize(340, 400);
        menu.setLocationRelativeTo(null);

        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(BG);
        p.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel logo = new JLabel("🌿 CoRide");
        logo.setFont(new Font("SansSerif", Font.BOLD, 22));
        logo.setForeground(ACCENT);
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel sub = new JLabel("Mock-up Screens Navigator");
        sub.setFont(SMALL);
        sub.setForeground(MUTED);
        sub.setAlignmentX(Component.CENTER_ALIGNMENT);

        p.add(logo);
        p.add(Box.createVerticalStrut(4));
        p.add(sub);
        p.add(Box.createVerticalStrut(20));
        p.add(makeSeparator());
        p.add(Box.createVerticalStrut(16));

        String[][] screens = {
            {"Οθόνη 1α — Σύνδεση",                    "UC1"},
            {"Οθόνη 1β — Εγγραφή & Προφίλ",           "UC1"},
            {"Οθόνη 2  — Dashboard",                   "UC8"},
            {"Οθόνη 3  — Προσφορά Διαδρομής",          "UC3"},
            {"Οθόνη 4  — Αναζήτηση & Κράτηση",         "UC4"},
            {"Οθόνη 5  — Πόντοι & Προνόμια",           "UC9"},
        };

        Runnable[] actions = {
            CoRideMockup::showLogin,
            CoRideMockup::showRegister,
            CoRideMockup::showDashboard,
            CoRideMockup::showRideOffer,
            CoRideMockup::showSearch,
            CoRideMockup::showPrivileges,
        };

        for (int i = 0; i < screens.length; i++) {
            final int idx = i;
            JPanel row = new JPanel(new BorderLayout(8, 0));
            row.setBackground(BG);
            row.setMaximumSize(new Dimension(280, 36));

            JButton btn = makeButton(screens[i][0]);
            btn.setHorizontalAlignment(SwingConstants.LEFT);
            btn.addActionListener(e -> actions[idx].run());

            JLabel tag = new JLabel(screens[i][1]);
            tag.setFont(SMALL);
            tag.setForeground(ACCENT);
            tag.setBorder(BorderFactory.createLineBorder(ACCENT2, 1));
            tag.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT2, 1),
                BorderFactory.createEmptyBorder(1, 5, 1, 5)
            ));

            row.add(btn, BorderLayout.CENTER);
            row.add(tag, BorderLayout.EAST);
            p.add(row);
            p.add(Box.createVerticalStrut(6));
        }

        menu.add(new JScrollPane(p));
        menu.setVisible(true);
    }

    // ══════════════════════════════════════════════════════════
    // SCREEN 1a — LOGIN
    // ══════════════════════════════════════════════════════════
    static void showLogin() {
        JFrame f = makeFrame("Οθόνη 1α — Σύνδεση  [UC1]", 360, 420);
        JPanel p = makePanel();

        p.add(makeHeader("🌿 CoRide", "Καλωσόρισες"));
        p.add(Box.createVerticalStrut(16));

        p.add(makeFieldRow("Email:", new JTextField(18)));
        p.add(Box.createVerticalStrut(8));
        p.add(makeFieldRow("Κωδικός:", new JPasswordField(18)));
        p.add(Box.createVerticalStrut(16));

        JButton login = makeAccentButton("ΣΥΝΔΕΣΗ");
        login.setAlignmentX(Component.CENTER_ALIGNMENT);
        login.addActionListener(e -> showDashboard());
        p.add(login);

        p.add(Box.createVerticalStrut(8));
        JButton reg = makeButton("Δεν έχεις λογαριασμό; Εγγραφή →");
        reg.setForeground(ACCENT);
        reg.setAlignmentX(Component.CENTER_ALIGNMENT);
        reg.addActionListener(e -> showRegister());
        p.add(reg);

        p.add(Box.createVerticalStrut(16));
        p.add(makeNote("* Σύνδεση με εταιρικά στοιχεία"));

        f.add(new JScrollPane(p));
        f.setVisible(true);
    }

    // ══════════════════════════════════════════════════════════
    // SCREEN 1b — REGISTER & PROFILE
    // ══════════════════════════════════════════════════════════
    static void showRegister() {
        JFrame f = makeFrame("Οθόνη 1β — Εγγραφή & Δημιουργία Προφίλ  [UC1]", 400, 560);
        JPanel p = makePanel();

        p.add(makeHeader("ΕΓΓΡΑΦΗ", "Βήμα 1/2 — Στοιχεία Χρήστη"));
        p.add(Box.createVerticalStrut(12));

        p.add(makeFieldRow("Όνομα:",       new JTextField(18)));
        p.add(Box.createVerticalStrut(6));
        p.add(makeFieldRow("Email:",       new JTextField(18)));
        p.add(Box.createVerticalStrut(6));
        p.add(makeFieldRow("Κωδικός:",     new JPasswordField(18)));
        p.add(Box.createVerticalStrut(12));

        p.add(makeSectionLabel("ΠΡΟΦΙΛ ΜΕΤΑΚΙΝΗΣΗΣ"));
        p.add(Box.createVerticalStrut(8));

        p.add(makeFieldRow("Διεύθυνση:",   new JTextField(18)));
        p.add(Box.createVerticalStrut(6));

        String[] roles = {"Οδηγός", "Επιβάτης", "Οδηγός & Επιβάτης"};
        JComboBox<String> roleBox = new JComboBox<>(roles);
        styleCombo(roleBox);
        p.add(makeFieldRow("Ρόλος:",       roleBox));
        p.add(Box.createVerticalStrut(6));

        p.add(makeFieldRow("Ωράριο:",      new JTextField("π.χ. 08:00 - 17:00", 18)));
        p.add(Box.createVerticalStrut(16));

        JButton next = makeAccentButton("ΕΠΟΜΕΝΟ →");
        next.setAlignmentX(Component.CENTER_ALIGNMENT);
        next.addActionListener(e -> {
            JOptionPane.showMessageDialog(null,
                "✅ Εγγραφή επιτυχής!\nΕπαλήθευση email αποστάλθηκε.",
                "Επιβεβαίωση", JOptionPane.INFORMATION_MESSAGE);
            showDashboard();
        });
        p.add(next);
        p.add(Box.createVerticalStrut(6));
        p.add(makeNote("* Επαλήθευση email απαιτείται για ενεργοποίηση (UC1a — include)"));

        f.add(new JScrollPane(p));
        f.setVisible(true);
    }

    // ══════════════════════════════════════════════════════════
    // SCREEN 2 — DASHBOARD
    // ══════════════════════════════════════════════════════════
    static void showDashboard() {
        JFrame f = makeFrame("Οθόνη 2 — Dashboard Εργαζόμενου  [UC8]", 380, 520);
        JPanel p = makePanel();

        p.add(makeHeader("🌿 CoRide", "Γεια, Νίκος!"));
        p.add(Box.createVerticalStrut(12));

        // Points card
        JPanel card = makeCard();
        card.add(makeRow("Πόντοι:", "320 π.", true));
        card.add(Box.createVerticalStrut(4));
        card.add(makeProgressBar(320, 500));
        card.add(Box.createVerticalStrut(4));
        JLabel lvl = new JLabel("Επίπεδο 3 — Δωροκάρτα 10€  |  Επόμενο: 500π.");
        lvl.setFont(SMALL);
        lvl.setForeground(MUTED);
        card.add(lvl);
        p.add(card);
        p.add(Box.createVerticalStrut(12));

        // Actions
        p.add(makeSectionLabel("ΓΡΗΓΟΡΕΣ ΕΝΕΡΓΕΙΕΣ"));
        p.add(Box.createVerticalStrut(6));

        JPanel btnRow = new JPanel(new GridLayout(1, 2, 8, 0));
        btnRow.setBackground(BG);
        btnRow.setMaximumSize(new Dimension(320, 44));

        JButton offer = makeAccentButton("🚗 Προσφορά Διαδρομής");
        offer.addActionListener(e -> showRideOffer());
        JButton search = makeButton("🔍 Αναζήτηση Διαδρομής");
        search.setBorder(BorderFactory.createLineBorder(ACCENT, 2));
        search.setForeground(ACCENT);
        search.addActionListener(e -> showSearch());

        btnRow.add(offer);
        btnRow.add(search);
        p.add(btnRow);
        p.add(Box.createVerticalStrut(12));

        // Stats
        p.add(makeSectionLabel("ΣΤΑΤΙΣΤΙΚΑ ΜΗΝΑ"));
        p.add(Box.createVerticalStrut(6));
        JPanel stats = makeCard();
        stats.add(makeRow("Μετακινήσεις:", "12", false));
        stats.add(makeRow("Εξοικονόμηση CO₂:", "~18 kg", false));
        stats.add(makeRow("Κοινά χιλιόμετρα:", "240 km", false));
        p.add(stats);
        p.add(Box.createVerticalStrut(12));

        // Nav bar
        p.add(makeSeparator());
        p.add(makeNavBar("Home"));

        f.add(new JScrollPane(p));
        f.setVisible(true);
    }

    // ══════════════════════════════════════════════════════════
    // SCREEN 3 — RIDE OFFER
    // ══════════════════════════════════════════════════════════
    static void showRideOffer() {
        JFrame f = makeFrame("Οθόνη 3 — Δημοσίευση Προσφοράς Διαδρομής  [UC3]", 400, 520);
        JPanel p = makePanel();

        JButton back = makeButton("← Πίσω");
        back.setForeground(ACCENT);
        back.addActionListener(e -> showDashboard());
        p.add(back);

        p.add(makeHeader("ΠΡΟΣΦΟΡΑ ΔΙΑΔΡΟΜΗΣ", "UC3 — Δημοσίευση Προσφοράς"));
        p.add(Box.createVerticalStrut(12));

        JTextField dateField = new JTextField("π.χ. 28/03/2026", 16);
        JTextField timeField = new JTextField("π.χ. 08:00", 16);
        JTextField startField = new JTextField("π.χ. Τρίκαλα, Καρδίτσης", 16);

        String[] seats = {"1 θέση", "2 θέσεις", "3 θέσεις", "4 θέσεις"};
        JComboBox<String> seatsBox = new JComboBox<>(seats);
        styleCombo(seatsBox);

        String[] vehicles = {"Toyota Yaris — ABC-1234", "Χωρίς όχημα"};
        JComboBox<String> vehicleBox = new JComboBox<>(vehicles);
        styleCombo(vehicleBox);

        p.add(makeFieldRow("Ημερομηνία:", dateField));
        p.add(Box.createVerticalStrut(6));
        p.add(makeFieldRow("Ώρα εκκίν.:", timeField));
        p.add(Box.createVerticalStrut(6));
        p.add(makeFieldRow("Εκκίνηση:",   startField));
        p.add(Box.createVerticalStrut(6));
        p.add(makeFieldRow("Θέσεις:",     seatsBox));
        p.add(Box.createVerticalStrut(6));
        p.add(makeFieldRow("Όχημα:",      vehicleBox));
        p.add(Box.createVerticalStrut(12));

        p.add(makeSectionLabel("ΕΠΑΛΗΘΕΥΣΗ"));
        p.add(Box.createVerticalStrut(6));
        JPanel preview = makeCard();
        preview.add(new JLabel("📅 " + "Παρ. 28/03/2026 — 08:00"));
        preview.add(new JLabel("📍 " + "Τρίκαλα, Καρδίτσης → Εταιρεία"));
        preview.add(new JLabel("👥 " + "2 διαθέσιμες θέσεις"));
        for (Component c : preview.getComponents()) { if (c instanceof JLabel) ((JLabel)c).setFont(SMALL); }
        p.add(preview);
        p.add(Box.createVerticalStrut(12));

        JPanel btnRow = new JPanel(new GridLayout(1, 2, 8, 0));
        btnRow.setBackground(BG);
        btnRow.setMaximumSize(new Dimension(320, 38));
        JButton cancel = makeButton("ΑΚΥΡΩΣΗ");
        cancel.setBorder(BorderFactory.createLineBorder(BORDER, 1));
        cancel.addActionListener(e -> showDashboard());
        JButton publish = makeAccentButton("ΔΗΜΟΣΙΕΥΣΗ ✓");
        publish.addActionListener(e -> {
            JOptionPane.showMessageDialog(null,
                "✅ Η προσφορά δημοσιεύτηκε!\nΘα ειδοποιηθείτε για κρατήσεις.",
                "Επιτυχία", JOptionPane.INFORMATION_MESSAGE);
            showDashboard();
        });
        btnRow.add(cancel);
        btnRow.add(publish);
        p.add(btnRow);
        p.add(Box.createVerticalStrut(6));
        p.add(makeNote("* Ελέγχεται: ημ/νία > σήμερα, θέσεις 1-4 (UC3 βήμα 4)"));

        f.add(new JScrollPane(p));
        f.setVisible(true);
    }

    // ══════════════════════════════════════════════════════════
    // SCREEN 4 — SEARCH & BOOK
    // ══════════════════════════════════════════════════════════
    static void showSearch() {
        JFrame f = makeFrame("Οθόνη 4 — Αναζήτηση & Κράτηση Διαδρομής  [UC4]", 420, 560);
        JPanel p = makePanel();

        JButton back = makeButton("← Πίσω");
        back.setForeground(ACCENT);
        back.addActionListener(e -> showDashboard());
        p.add(back);

        p.add(makeHeader("ΑΝΑΖΗΤΗΣΗ ΔΙΑΔΡΟΜΗΣ", "UC4 — Αναζήτηση & Κράτηση"));
        p.add(Box.createVerticalStrut(12));

        p.add(makeFieldRow("Ημερομηνία:", new JTextField("π.χ. 28/03/2026", 16)));
        p.add(Box.createVerticalStrut(6));
        p.add(makeFieldRow("Ώρα:",         new JTextField("π.χ. 08:00", 16)));
        p.add(Box.createVerticalStrut(6));
        p.add(makeFieldRow("Περιοχή:",     new JTextField("π.χ. Τρίκαλα", 16)));
        p.add(Box.createVerticalStrut(10));

        JButton search = makeAccentButton("🔍 ΑΝΑΖΗΤΗΣΗ");
        search.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(search);
        p.add(Box.createVerticalStrut(12));

        p.add(makeSectionLabel("ΑΠΟΤΕΛΕΣΜΑΤΑ (2)"));
        p.add(Box.createVerticalStrut(6));

        // Results
        String[][] results = {
            {"Γιώργος Π.", "08:15", "Τρίκαλα, Καρδίτσης", "2", "4.8"},
            {"Μαρία Κ.",   "08:30", "Τρίκαλα, Κεντρική",  "1", "4.5"},
        };

        for (String[] r : results) {
            JPanel card = makeCard();
            JPanel top = new JPanel(new BorderLayout());
            top.setBackground(Color.WHITE);
            JLabel name = new JLabel(r[0]);
            name.setFont(BOLD_SM);
            JLabel time = new JLabel(r[1]);
            time.setFont(BOLD_SM);
            time.setForeground(ACCENT);
            top.add(name, BorderLayout.WEST);
            top.add(time, BorderLayout.EAST);
            card.add(top);

            JLabel info = new JLabel("📍 " + r[2] + "  ·  👥 " + r[3] + " θέση  ·  ⭐ " + r[4]);
            info.setFont(SMALL);
            info.setForeground(MUTED);
            card.add(info);

            JButton book = makeAccentButton("ΚΡΑΤΗΣΗ ✓");
            book.setFont(SMALL);
            final String nm = r[0];
            book.addActionListener(e -> {
                JOptionPane.showMessageDialog(null,
                    "✅ Η κράτηση επιβεβαιώθηκε!\nΟδηγός: " + nm + "\nΩρα: " + r[1],
                    "Κράτηση", JOptionPane.INFORMATION_MESSAGE);
            });
            card.add(Box.createVerticalStrut(4));
            card.add(book);
            p.add(card);
            p.add(Box.createVerticalStrut(6));
        }

        p.add(makeNote("* Εναλλακτική ροή Α1: Αν δεν βρεθούν αποτελέσματα → μήνυμα + εναλλακτικές ημ."));
        p.add(makeNote("* Εναλλακτική ροή Α2: Αν η θέση έχει κρατηθεί → επιστροφή στη λίστα"));

        f.add(new JScrollPane(p));
        f.setVisible(true);
    }

    // ══════════════════════════════════════════════════════════
    // SCREEN 5 — POINTS & PRIVILEGES
    // ══════════════════════════════════════════════════════════
    static void showPrivileges() {
        JFrame f = makeFrame("Οθόνη 5 — Πόντοι & Προνόμια  [UC9]", 420, 560);
        JPanel p = makePanel();

        p.add(makeHeader("🏆 ΠΟΝΤΟΙ & ΠΡΟΝΟΜΙΑ", "UC9 — Gamification"));
        p.add(Box.createVerticalStrut(12));

        // Points summary
        JPanel card = makeCard();
        JLabel pts = new JLabel("320 π.", SwingConstants.CENTER);
        pts.setFont(new Font("SansSerif", Font.BOLD, 26));
        pts.setForeground(ACCENT);
        card.add(pts);
        card.add(Box.createVerticalStrut(4));
        card.add(makeProgressBar(320, 500));
        card.add(Box.createVerticalStrut(4));
        JLabel lvlLbl = new JLabel("Επίπεδο 3 — Δωροκάρτα 10€  |  180π. ακόμα για Επ.4");
        lvlLbl.setFont(SMALL);
        lvlLbl.setForeground(MUTED);
        card.add(lvlLbl);
        p.add(card);
        p.add(Box.createVerticalStrut(12));

        // Privileges table
        p.add(makeSectionLabel("ΕΠΙΠΕΔΑ ΠΡΟΝΟΜΙΩΝ"));
        p.add(Box.createVerticalStrut(6));

        String[] cols = {"Επ.", "Πόντοι", "Προνόμιο", "Κατάσταση"};
        Object[][] rows = {
            {"1", "50π.",   "Green Commuter Badge",  "✅ Ξεκλείδωτο"},
            {"2", "150π.",  "Προτεραιότητα Parking", "✅ Ξεκλείδωτο"},
            {"3", "300π.",  "Δωροκάρτα 10€",         "✅ Ξεκλείδωτο"},
            {"4", "500π.",  "Extra Τηλεργασία",       "🔒 180π. ακόμα"},
            {"5", "800π.",  "Μειωμένη Ασφάλιση",      "🔒 Κλειδωμένο"},
            {"6", "1200π.", "Green MVP Trophy",        "🔒 Κλειδωμένο"},
        };

        JTable table = new JTable(rows, cols) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        table.setFont(SMALL);
        table.setRowHeight(24);
        table.getTableHeader().setFont(BOLD_SM);
        table.getTableHeader().setBackground(SUBTLE);
        table.setGridColor(BORDER);
        table.setShowGrid(true);
        table.setSelectionBackground(SUBTLE);

        // Colour locked rows
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable t, Object v,
                    boolean sel, boolean foc, int row, int col) {
                super.getTableCellRendererComponent(t, v, sel, foc, row, col);
                setBackground(row < 3 ? new Color(0xF0FFF4) : new Color(0xFAFAFA));
                setForeground(row < 3 ? ACCENT : MUTED);
                setBorder(BorderFactory.createEmptyBorder(0, 6, 0, 6));
                return this;
            }
        });

        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setPreferredSize(new Dimension(360, 160));
        tableScroll.setMaximumSize(new Dimension(360, 160));
        p.add(tableScroll);
        p.add(Box.createVerticalStrut(12));

        JButton redeem = makeAccentButton("ΕΞΑΡΓΥΡΩΣΗ ΠΡΟΝΟΜΙΟΥ");
        redeem.setAlignmentX(Component.CENTER_ALIGNMENT);
        redeem.addActionListener(e ->
            JOptionPane.showMessageDialog(null,
                "✅ Η εξαργύρωση καταχωρήθηκε!\nΘα λάβετε επιβεβαίωση μέσω email.",
                "Εξαργύρωση", JOptionPane.INFORMATION_MESSAGE));
        p.add(redeem);
        p.add(Box.createVerticalStrut(8));
        p.add(makeNote("* Πόντοι αποδίδονται ανά ολοκληρωμένη κοινή μετακίνηση (Trip)"));

        p.add(Box.createVerticalStrut(8));
        p.add(makeSeparator());
        p.add(makeNavBar("Πόντοι"));

        f.add(new JScrollPane(p));
        f.setVisible(true);
    }

    // ══════════════════════════════════════════════════════════
    // UI HELPERS
    // ══════════════════════════════════════════════════════════

    static JFrame makeFrame(String title, int w, int h) {
        JFrame f = new JFrame(title);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setSize(w, h);
        f.setLocationRelativeTo(null);
        return f;
    }

    static JPanel makePanel() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(BG);
        p.setBorder(BorderFactory.createEmptyBorder(20, 24, 20, 24));
        return p;
    }

    static JPanel makeHeader(String title, String subtitle) {
        JPanel h = new JPanel();
        h.setLayout(new BoxLayout(h, BoxLayout.Y_AXIS));
        h.setBackground(SUBTLE);
        h.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 4, 0, 0, ACCENT),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        h.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        JLabel t = new JLabel(title);
        t.setFont(TITLE);
        t.setForeground(ACCENT);
        JLabel s = new JLabel(subtitle);
        s.setFont(SMALL);
        s.setForeground(MUTED);
        h.add(t);
        h.add(s);
        return h;
    }

    static JPanel makeCard() {
        JPanel c = new JPanel();
        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
        c.setBackground(Color.WHITE);
        c.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER, 1),
            BorderFactory.createEmptyBorder(10, 14, 10, 14)
        ));
        c.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));
        return c;
    }

    static JPanel makeFieldRow(String label, JComponent field) {
        JPanel row = new JPanel(new BorderLayout(8, 0));
        row.setBackground(BG);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        JLabel lbl = new JLabel(label);
        lbl.setFont(BODY);
        lbl.setPreferredSize(new Dimension(100, 24));
        if (field instanceof JTextField) ((JTextField) field).setFont(BODY);
        row.add(lbl, BorderLayout.WEST);
        row.add(field, BorderLayout.CENTER);
        return row;
    }

    static JPanel makeRow(String label, String value, boolean bold) {
        JPanel row = new JPanel(new BorderLayout());
        row.setBackground(Color.WHITE);
        JLabel l = new JLabel(label);
        l.setFont(SMALL);
        JLabel v = new JLabel(value);
        v.setFont(bold ? BOLD_SM : SMALL);
        v.setForeground(bold ? ACCENT : FG);
        row.add(l, BorderLayout.WEST);
        row.add(v, BorderLayout.EAST);
        return row;
    }

    static JButton makeButton(String text) {
        JButton b = new JButton(text);
        b.setFont(BODY);
        b.setBackground(BG);
        b.setForeground(FG);
        b.setBorderPainted(true);
        b.setFocusPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return b;
    }

    static JButton makeAccentButton(String text) {
        JButton b = new JButton(text);
        b.setFont(BOLD_SM);
        b.setBackground(ACCENT);
        b.setForeground(Color.WHITE);
        b.setOpaque(true);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setPreferredSize(new Dimension(160, 34));
        return b;
    }

    static JLabel makeSectionLabel(String text) {
        JLabel l = new JLabel(text);
        l.setFont(new Font("SansSerif", Font.BOLD, 10));
        l.setForeground(MUTED);
        return l;
    }

    static JLabel makeNote(String text) {
        JLabel l = new JLabel("<html><i>" + text + "</i></html>");
        l.setFont(new Font("SansSerif", Font.ITALIC, 10));
        l.setForeground(MUTED);
        return l;
    }

    static JSeparator makeSeparator() {
        JSeparator s = new JSeparator();
        s.setMaximumSize(new Dimension(Integer.MAX_VALUE, 2));
        s.setForeground(BORDER);
        return s;
    }

    static JPanel makeProgressBar(int current, int max) {
        JPanel bar = new JPanel(null) {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(BORDER);
                g.fillRect(0, 0, getWidth(), getHeight());
                g.setColor(ACCENT2);
                g.fillRect(0, 0, (int)((double) current / max * getWidth()), getHeight());
            }
        };
        bar.setPreferredSize(new Dimension(280, 8));
        bar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 8));
        return bar;
    }

    static JPanel makeNavBar(String active) {
        String[] items = {"🏠 Home", "🚗 Διαδρομές", "🏆 Πόντοι", "👤 Προφίλ"};
        JPanel nav = new JPanel(new GridLayout(1, 4));
        nav.setBackground(BG);
        nav.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, BORDER));
        nav.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        for (String item : items) {
            JButton b = new JButton(item);
            b.setFont(new Font("SansSerif", Font.PLAIN, 9));
            b.setBorderPainted(false);
            b.setFocusPainted(false);
            boolean isActive = item.contains(active);
            b.setBackground(isActive ? SUBTLE : BG);
            b.setForeground(isActive ? ACCENT : MUTED);
            nav.add(b);
        }
        return nav;
    }

    static void styleCombo(JComboBox<?> c) {
        c.setFont(BODY);
        c.setBackground(Color.WHITE);
    }
}
