import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * περιλαμβανει τις οθονες που περιγραφονται στις αναφορες μας, εχουν φτιαχτει με cardLayout
 * για να μην ανοιγει ξεχωριστο παραθυρο σε καθε πατημα λειτουργιας
 *
 * -προσομοιωση λειτουργιων οπως login, register, booking, rating
 * -δεδομενα mock (χωρις υλοποιημενο backend / database)
 * - σκοπος του κωδικα ειναι να δωσουμε ενα δειγμα λειτουργιας της ροης και το UI
 * - δεν εχει γινει hashing δεδομενων απο καποιο site, ειναι dummy μονο για την προσομοιωση
 */
class CoRideMockup_v01 {
// ορισμος χρωματων που χρησιμοποιουμε στην εφαρμογη
    // ── Palette ───────────────────────────────────────────────────────────────
    static final Color BG        = new Color(0xF5F0FA);
    static final Color FG        = new Color(0x1A1A1A);
    static final Color ACCENT    = new Color(0x091E57);
    static final Color ACCENT2   = new Color(0x213FA1);
    static final Color SUBTLE    = new Color(0xE8F5E9);
    static final Color BORDER    = new Color(0xCCCCCC);
    static final Color MUTED     = new Color(0x388485);
    static final Color CLR_ERROR = new Color(0xD32F2F);
    static final Color ERROR_BG  = new Color(0xFFEBEE);
    static final Color WARN_BG   = new Color(0xFFF8E1);
    static final Color WARN_FG   = new Color(0xE65100);
    static final Color INFO_BG   = new Color(0xE3F2FD);
    static final Color INFO_FG   = new Color(0x1565C0);

    static final Font TITLE   = new Font("SansSerif", Font.BOLD,  14);
    static final Font BODY    = new Font("SansSerif", Font.PLAIN, 12);
    static final Font SMALL   = new Font("SansSerif", Font.PLAIN, 10);
    static final Font BOLD_SM = new Font("SansSerif", Font.BOLD,  11);

    // ── Mock Data ─────────────────────────────────────────────────────────────
    static final String USER_NAME   = "Νίκος Παπαδόπουλος";
    static final String USER_EMAIL  = "n.papadopoulos@company.gr";
    static final int    USER_POINTS = 320;
    static final String TODAY    = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    static final String TOMORROW = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

    // ── CardLayout globals ────────────────────────────────────────────────────
    static JFrame     mainFrame;
    static JPanel     cardPanel;
    static CardLayout cardLayout;
 // ονομαζουμε τις οθονες που εμφανιζονται στην εφαρμογη
    static final String CARD_MENU     = "MENU";
    static final String CARD_LOGIN    = "LOGIN";
    static final String CARD_REGISTER = "REGISTER";
    static final String CARD_DASH     = "DASHBOARD";
    static final String CARD_OFFER    = "RIDE_OFFER";
    static final String CARD_SEARCH   = "SEARCH";
    static final String CARD_BOOKING  = "BOOKING";
    static final String CARD_TRIP     = "TRIP";
    static final String CARD_RATING   = "RATING";
    static final String CARD_POINTS   = "POINTS";
    static final String CARD_PROFILE  = "PROFILE";

    // ── Entry point ───────────────────────────────────────────────────────────
    // δημιουργει το κυριο παραθυρο και φορτωνει ολα τα στοιχεια
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UIManager.put("Panel.background", BG);

            mainFrame = new JFrame("CoRide v0.2 — Mock-up");
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setSize(460, 660);
            mainFrame.setLocationRelativeTo(null);

            cardLayout = new CardLayout();
            cardPanel  = new JPanel(cardLayout);

            cardPanel.add(buildMenu(),         CARD_MENU);
            cardPanel.add(buildLogin(),        CARD_LOGIN);
            cardPanel.add(buildRegister(),     CARD_REGISTER);
            cardPanel.add(buildDashboard(),    CARD_DASH);
            cardPanel.add(buildRideOffer(),    CARD_OFFER);
            cardPanel.add(buildSearch(),       CARD_SEARCH);
            cardPanel.add(buildBooking(),      CARD_BOOKING);
            cardPanel.add(buildTripComplete(), CARD_TRIP);
            cardPanel.add(buildRating(),       CARD_RATING);
            cardPanel.add(buildPrivileges(),   CARD_POINTS);
            cardPanel.add(buildProfile(),      CARD_PROFILE);

            mainFrame.add(cardPanel);
            mainFrame.setVisible(true);

            showCard(CARD_MENU);
        });
    }
// εμφανιζει την αντιστοιχη οθονη που επιλεγουμε απο τον 'καταλογο' με τις λειτουργιες (dashboard)
    static void showCard(String name) {
        cardLayout.show(cardPanel, name);
    }

    // ══════════════════════════════════════════════════════════════════════════
    // MENU
    // ══════════════════════════════════════════════════════════════════════════
    static JPanel buildMenu() {
        JPanel p = makeScrollPanel();

        JLabel logo = new JLabel("🌿 CoRide");
        logo.setFont(new Font("SansSerif", Font.BOLD, 22));
        logo.setForeground(ACCENT);
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel ver = new JLabel("Mock-up v0.2  |  " + TODAY);
        ver.setFont(SMALL);
        ver.setForeground(MUTED);
        ver.setAlignmentX(Component.CENTER_ALIGNMENT);

        p.add(logo);
        p.add(Box.createVerticalStrut(4));
        p.add(ver);
        p.add(Box.createVerticalStrut(16));
        p.add(makeSeparator());
        p.add(Box.createVerticalStrut(12));

        JPanel badge = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        badge.setBackground(BG);
        badge.setMaximumSize(new Dimension(Integer.MAX_VALUE, 24));
        p.add(badge);
        p.add(Box.createVerticalStrut(10));

        Object[][] screens = {
                {"Σύνδεση",               CARD_LOGIN},
                {"Εγγραφή",               CARD_REGISTER},
                {"Dashboard",              CARD_DASH},
                {"Προσφορά Διαδρομής",    CARD_OFFER},
                {"Αναζήτηση & Κράτηση",   CARD_SEARCH},
                {"Επιβεβαίωση Κράτησης",  CARD_BOOKING},
                {"Ολοκλήρωση Μετακίνησης",CARD_TRIP},
                {"Αξιολόγηση",             CARD_RATING},
                {"Πόντοι & Προνόμια",      CARD_POINTS},
                {"Προφίλ Χρήστη",          CARD_PROFILE},
        };

        for (Object[] s : screens) {
            String card = (String) s[1];
            JButton btn = makeButton((String) s[0]);
            btn.setHorizontalAlignment(SwingConstants.LEFT);
            btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
            btn.addActionListener(e -> showCard(card));
            p.add(btn);
            p.add(Box.createVerticalStrut(5));
        }

        return wrapScroll(p);
    }

    // ══════════════════════════════════════════════════════════════════════════
    // SCREEN 1a — LOGIN
    // ══════════════════════════════════════════════════════════════════════════
    static JPanel buildLogin() {
        JPanel p = makeScrollPanel();

        p.add(makeBackButton(CARD_MENU));
        p.add(makeHeader("🌿 CoRide", "Καλωσόρισες — Σύνδεση με εταιρικά στοιχεία"));
        p.add(Box.createVerticalStrut(20));

        JTextField emailField = new JTextField(18);
        JPasswordField pwdField = new JPasswordField(18);

        p.add(makeFieldRow("Email:", emailField));
        p.add(Box.createVerticalStrut(8));
        p.add(makeFieldRow("Κωδικός:", pwdField));
        p.add(Box.createVerticalStrut(6));
        p.add(makeAltNote("ⓘ Αποδεκτά emails: @company.gr μόνο"));
        p.add(Box.createVerticalStrut(16));

        JLabel errLbl = makeErrLabel();
        p.add(errLbl);
        p.add(Box.createVerticalStrut(4));

        JButton login = makeAccentButton("ΣΥΝΔΕΣΗ");
        login.setAlignmentX(Component.CENTER_ALIGNMENT);
        login.addActionListener(e -> {
            String email = emailField.getText().trim();
            String pwd   = new String(pwdField.getPassword()).trim();
            boolean valid = true;
            if (email.isEmpty()) { highlightError(emailField); valid = false; } else resetField(emailField);
            if (pwd.isEmpty())   { highlightError(pwdField);   valid = false; } else resetField(pwdField);
            if (!valid) { errLbl.setText("⚠ Συμπλήρωσε όλα τα υποχρεωτικά πεδία."); return; }
            if (!email.endsWith("@company.gr")) {
                highlightError(emailField);
                errLbl.setText("⚠ Μη αποδεκτό email domain.");
                return;
            }
            errLbl.setText(" ");
            showCard(CARD_DASH);
        });
        p.add(login);
        p.add(Box.createVerticalStrut(10));

        JButton forgot = makeButton("Ξέχασες τον κωδικό; →");
        forgot.setForeground(ACCENT);
        forgot.setAlignmentX(Component.CENTER_ALIGNMENT);
        forgot.addActionListener(e -> JOptionPane.showMessageDialog(mainFrame,
                "Email επαναφοράς κωδικού θα αποσταλεί.",
                "Επαναφορά Κωδικού", JOptionPane.INFORMATION_MESSAGE));
        p.add(forgot);

        JButton reg = makeButton("Δεν έχεις λογαριασμό; Εγγραφή →");
        reg.setForeground(ACCENT);
        reg.setAlignmentX(Component.CENTER_ALIGNMENT);
        reg.addActionListener(e -> showCard(CARD_REGISTER));
        p.add(reg);

        return wrapScroll(p);
    }

    // ══════════════════════════════════════════════════════════════════════════
    // SCREEN 1b — REGISTER
    // ══════════════════════════════════════════════════════════════════════════
    static JPanel buildRegister() {
        JPanel p = makeScrollPanel();

        p.add(makeBackButton(CARD_MENU));
        p.add(makeHeader("ΕΓΓΡΑΦΗ", "Βήμα 1/2 — Στοιχεία Χρήστη"));
        p.add(Box.createVerticalStrut(12));

        JTextField nameField  = new JTextField(18);
        JTextField emailField = new JTextField(18);
        JPasswordField pwdField = new JPasswordField(18);
        JTextField addrField  = new JTextField(18);
        JComboBox<String> roleBox = new JComboBox<>(new String[]{"Επιβάτης", "Οδηγός", "Οδηγός & Επιβάτης"});
        styleCombo(roleBox);
        JTextField schedField = new JTextField("08:00 - 17:00", 18);

        p.add(makeFieldRow("Ονοματεπώνυμο: *", nameField));
        p.add(Box.createVerticalStrut(6));
        p.add(makeFieldRow("Email: *",          emailField));
        p.add(Box.createVerticalStrut(4));
        p.add(makeAltNote("ⓘ Απαιτείται εταιρικό email @company.gr"));
        p.add(Box.createVerticalStrut(6));
        p.add(makeFieldRow("Κωδικός: *",        pwdField));
        p.add(Box.createVerticalStrut(6));
        p.add(makeFieldRow("Διεύθυνση:",         addrField));
        p.add(Box.createVerticalStrut(6));
        p.add(makeFieldRow("Ρόλος:",             roleBox));
        p.add(Box.createVerticalStrut(6));
        p.add(makeFieldRow("Ωράριο:",            schedField));
        p.add(Box.createVerticalStrut(10));

        JLabel errLbl = makeErrLabel();
        p.add(errLbl);
        p.add(Box.createVerticalStrut(6));

        JButton next = makeAccentButton("ΕΓΓΡΑΦΗ ✓");
        next.setAlignmentX(Component.CENTER_ALIGNMENT);
        next.addActionListener(e -> {
            boolean valid = true;
            if (nameField.getText().trim().isEmpty())  { highlightError(nameField);  valid = false; } else resetField(nameField);
            if (emailField.getText().trim().isEmpty()) { highlightError(emailField); valid = false; } else resetField(emailField);
            if (new String(pwdField.getPassword()).trim().isEmpty()) { highlightError(pwdField); valid = false; } else resetField(pwdField);
            if (!valid) { errLbl.setText("⚠ Τα πεδία με * είναι υποχρεωτικά."); return; }
            if (!emailField.getText().trim().endsWith("@company.gr")) {
                highlightError(emailField);
                errLbl.setText("⚠ Μη αποδεκτό domain.");
                return;
            }
            errLbl.setText(" ");
            JOptionPane.showMessageDialog(mainFrame,
                    "✅ Εγγραφή επιτυχής!\nEmail επαλήθευσης εστάλη στο " + emailField.getText().trim(),
                    "Επιβεβαίωση Εγγραφής", JOptionPane.INFORMATION_MESSAGE);
            showCard(CARD_DASH);
        });
        p.add(next);
        p.add(Box.createVerticalStrut(6));
        p.add(makeNote("* Υποχρεωτικά πεδία  |  Απαιτείται επαλήθευση email"));

        return wrapScroll(p);
    }

    // ══════════════════════════════════════════════════════════════════════════
    // SCREEN 2 — DASHBOARD
    // ══════════════════════════════════════════════════════════════════════════
    static JPanel buildDashboard() {
        JPanel p = makeScrollPanel();

        p.add(makeBackButton(CARD_MENU));
        p.add(makeHeader("CoRide", "Γεια, " + USER_NAME.split(" ")[0] + "!  ·  " + TODAY));
        p.add(Box.createVerticalStrut(12));

        JPanel card = makeClickableCard("ΠΟΝΤΟΙ  →  Δες Προνόμια", () -> showCard(CARD_POINTS));
        card.add(makeRow("Πόντοι:", USER_POINTS + " π.", true));
        card.add(Box.createVerticalStrut(4));
        card.add(makeProgressBar(USER_POINTS, 500));
        card.add(Box.createVerticalStrut(4));
        card.add(new JLabel("Επίπεδο 3  |  Δωροκάρτα 10€  |  Επόμενο: 500π.  (180π. ακόμα)") {{
            setFont(SMALL); setForeground(MUTED); }});
        p.add(card);
        p.add(Box.createVerticalStrut(12));

        p.add(makeSectionLabel("ΕΚΚΡΕΜΕΙΣ ΚΡΑΤΗΣΕΙΣ"));
        p.add(Box.createVerticalStrut(6));
        JPanel pendCard = makeClickableCard("📋 Εκκρεμεί επιβεβαίωση από οδηγό  →  Δες", () -> showCard(CARD_BOOKING));
        pendCard.add(new JLabel("Γιώργος Π.  ·  " + TOMORROW + "  ·  08:15") {{
            setFont(SMALL); setForeground(MUTED); }});
        pendCard.add(new JLabel("Κατάσταση:  Αναμονή Επιβεβαίωσης") {{
            setFont(SMALL); setForeground(WARN_FG); }});
        p.add(pendCard);
        p.add(Box.createVerticalStrut(12));

        p.add(makeSectionLabel("ΓΡΗΓΟΡΕΣ ΕΝΕΡΓΕΙΕΣ"));
        p.add(Box.createVerticalStrut(6));
        JPanel btnRow = new JPanel(new GridLayout(1, 2, 8, 0));
        btnRow.setBackground(BG);
        btnRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
        JButton offer  = makeAccentButton("Προσφορά");
        offer.addActionListener(e -> showCard(CARD_OFFER));
        JButton search = makeButton("Αναζήτηση");
        search.setBorder(BorderFactory.createLineBorder(ACCENT, 2));
        search.setForeground(ACCENT);
        search.addActionListener(e -> showCard(CARD_SEARCH));
        btnRow.add(offer);
        btnRow.add(search);
        p.add(btnRow);
        p.add(Box.createVerticalStrut(12));

        p.add(makeSectionLabel("ΣΤΑΤΙΣΤΙΚΑ ΜΗΝΑ"));
        p.add(Box.createVerticalStrut(6));
        JPanel stats = makeCard();
        stats.add(makeRow("Μετακινήσεις:", "12", false));
        stats.add(makeRow("Εξοικονόμηση CO₂:", "~18.4 kg", false));
        stats.add(makeRow("Κοινά χιλιόμετρα:", "247 km", false));
        stats.add(makeRow("Θέση Leaderboard:", "#4 / 38", false));
        p.add(stats);
        p.add(Box.createVerticalStrut(12));

        p.add(makeSeparator());
        p.add(makeNavBar("Home"));

        return wrapScroll(p);
    }

    // ══════════════════════════════════════════════════════════════════════════
    // SCREEN 3 — RIDE OFFER
    // ══════════════════════════════════════════════════════════════════════════
    static JPanel buildRideOffer() {
        JPanel p = makeScrollPanel();

        p.add(makeBackButton(CARD_DASH));
        p.add(makeHeader("ΠΡΟΣΦΟΡΑ ΔΙΑΔΡΟΜΗΣ", "Δημοσίευση Προσφοράς"));
        p.add(Box.createVerticalStrut(12));

        JTextField dateField  = new JTextField(TOMORROW, 16);
        JTextField timeField  = new JTextField("08:00", 16);
        JTextField startField = new JTextField("Τρίκαλα, Καρδίτσης 14", 16);
        JTextField destField  = new JTextField("Βιομηχανική Ζώνη, Λάρισα", 16);
        JComboBox<String> seatsBox = new JComboBox<>(new String[]{"1 θέση","2 θέσεις","3 θέσεις","4 θέσεις"});
        seatsBox.setSelectedIndex(1);
        styleCombo(seatsBox);
        JComboBox<String> vehicleBox = new JComboBox<>(new String[]{"Toyota Yaris — ΑΒΓ-1234", "+ Προσθήκη νέου οχήματος"});
        styleCombo(vehicleBox);

        p.add(makeFieldRow("Ημερομηνία: *",    dateField));
        p.add(Box.createVerticalStrut(6));
        p.add(makeFieldRow("Ώρα εκκίνησης: *", timeField));
        p.add(Box.createVerticalStrut(6));
        p.add(makeFieldRow("Εκκίνηση: *",      startField));
        p.add(Box.createVerticalStrut(6));
        p.add(makeFieldRow("Προορισμός: *",    destField));
        p.add(Box.createVerticalStrut(6));
        p.add(makeFieldRow("Θέσεις:",          seatsBox));
        p.add(Box.createVerticalStrut(6));
        p.add(makeFieldRow("Όχημα:",           vehicleBox));
        p.add(Box.createVerticalStrut(12));

        p.add(makeSectionLabel("ΠΡΟΕΠΙΣΚΟΠΗΣΗ ΔΙΑΔΡΟΜΗΣ"));
        p.add(Box.createVerticalStrut(6));
        JPanel mapPanel = new JPanel();
        mapPanel.setBackground(new Color(0xE8F4EA));
        mapPanel.setBorder(BorderFactory.createLineBorder(ACCENT2, 1));
        mapPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        mapPanel.add(new JLabel("~42 km  |  ~38 λεπτά  |  CO₂ εξοικονόμηση: ~3.2 kg") {{
            setFont(SMALL); setForeground(ACCENT); }});
        p.add(mapPanel);
        p.add(Box.createVerticalStrut(12));

        JLabel errLbl = makeErrLabel();
        p.add(errLbl);
        p.add(Box.createVerticalStrut(4));

        JPanel btnRow = new JPanel(new GridLayout(1, 2, 8, 0));
        btnRow.setBackground(BG);
        btnRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        JButton cancel = makeButton("ΑΚΥΡΩΣΗ");
        cancel.setBorder(BorderFactory.createLineBorder(BORDER, 1));
        cancel.addActionListener(e -> showCard(CARD_DASH));
        JButton publish = makeAccentButton("ΔΗΜΟΣΙΕΥΣΗ ✓");
        publish.addActionListener(e -> {
            boolean valid = true;
            for (JTextField tf : new JTextField[]{dateField, timeField, startField, destField}) {
                if (tf.getText().trim().isEmpty()) { highlightError(tf); valid = false; } else resetField(tf);
            }
            if (!valid) { errLbl.setText("⚠ Τα πεδία με * είναι υποχρεωτικά."); return; }
            if (dateField.getText().equals(TOMORROW) && timeField.getText().equals("08:00")) {
                int res = JOptionPane.showConfirmDialog(mainFrame,
                        "⚠ Υπάρχει ήδη ενεργή προσφορά για " + TOMORROW + " 08:00.\nΘέλεις να συνεχίσεις;",
                        "Διπλότυπη Προσφορά", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (res != JOptionPane.YES_OPTION) return;
            }
            errLbl.setText(" ");
            JOptionPane.showMessageDialog(mainFrame,
                    "Η προσφορά δημοσιεύτηκε!\n" + TOMORROW + " — 08:00\n" +
                            startField.getText().trim() + " → " + destField.getText().trim() +
                            "\n(RideOffer δημιουργήθηκε, status=open)",
                    "Επιτυχία", JOptionPane.INFORMATION_MESSAGE);
            showCard(CARD_DASH);
        });
        btnRow.add(cancel);
        btnRow.add(publish);
        p.add(btnRow);
        p.add(Box.createVerticalStrut(6));
        p.add(makeNote("* Υποχρεωτικά  |  Ημ/νία πρέπει να είναι > σήμερα  |  Θέσεις 1-4"));

        return wrapScroll(p);
    }

    // ══════════════════════════════════════════════════════════════════════════
    // SCREEN 4 — SEARCH & BOOK
    // ══════════════════════════════════════════════════════════════════════════
    static JPanel buildSearch() {
        JPanel p = makeScrollPanel();

        p.add(makeBackButton(CARD_DASH));
        p.add(makeHeader("ΑΝΑΖΗΤΗΣΗ ΔΙΑΔΡΟΜΗΣ", "RideOffer"));
        p.add(Box.createVerticalStrut(12));

        JTextField dateField = new JTextField(TOMORROW, 16);
        JTextField timeField = new JTextField("08:00", 16);
        JTextField areaField = new JTextField("Τρίκαλα", 16);

        p.add(makeFieldRow("Ημερομηνία: *",      dateField));
        p.add(Box.createVerticalStrut(6));
        p.add(makeFieldRow("Ώρα (περίπου):",     timeField));
        p.add(Box.createVerticalStrut(6));
        p.add(makeFieldRow("Περιοχή εκκίνησης:", areaField));
        p.add(Box.createVerticalStrut(10));

        JLabel errLbl = makeErrLabel();
        p.add(errLbl);

        JButton search = makeAccentButton("ΑΝΑΖΗΤΗΣΗ");
        search.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        resultsPanel.setBackground(BG);
        resultsPanel.setVisible(false);

        search.addActionListener(e -> {
            if (dateField.getText().trim().isEmpty()) {
                highlightError(dateField);
                errLbl.setText("⚠ Η ημερομηνία είναι υποχρεωτική.");
                return;
            }
            resetField(dateField);
            errLbl.setText(" ");
            resultsPanel.removeAll();

            if (areaField.getText().trim().equalsIgnoreCase("Άγνωστο")) {
                resultsPanel.add(makeErrorCard(
                        "⚠ Δεν βρέθηκαν διαδρομές για τα κριτήρια σου.",
                        "Δοκίμασε άλλη ημερομηνία ή περιοχή."));
            } else {
                resultsPanel.add(makeSectionLabel("ΑΠΟΤΕΛΕΣΜΑΤΑ (2)"));
                resultsPanel.add(Box.createVerticalStrut(6));

                String[][] results = {
                        {"Γιώργος Παπαδόπουλος","08:15","Τρίκαλα, Καρδίτσης 14","2","4.8","available"},
                        {"Μαρία Κωνσταντίνου",  "08:30","Τρίκαλα, Κεντρική Πλ.","0","4.5","full"},
                };

                for (String[] r : results) {
                    boolean full = r[5].equals("full");
                    JPanel card = makeCard();
                    JPanel top = new JPanel(new BorderLayout());
                    top.setBackground(Color.WHITE);
                    JLabel name = new JLabel(r[0]); name.setFont(BOLD_SM);
                    JLabel time = new JLabel(r[1]); time.setFont(BOLD_SM); time.setForeground(ACCENT);
                    top.add(name, BorderLayout.WEST);
                    top.add(time, BorderLayout.EAST);
                    card.add(top);
                    card.add(new JLabel("📍 " + r[2] + "  ·  👥 " + r[3] + " θέσεις  ·  ⭐ " + r[4]) {{
                        setFont(SMALL); setForeground(MUTED); }});
                    if (full) {
                        card.add(new JLabel("🔴 Πλήρης — Δεν υπάρχουν διαθέσιμες θέσεις") {{
                            setFont(SMALL); setForeground(CLR_ERROR); }});
                    } else {
                        JButton book = makeAccentButton("ΚΡΑΤΗΣΗ ✓");
                        book.setFont(SMALL);
                        book.addActionListener(ev -> {
                            JOptionPane.showMessageDialog(mainFrame,
                                    "✅ Αίτημα κράτησης εστάλη!\nΟδηγός: " + r[0] + "  ·  " + r[1],
                                    "Κράτηση", JOptionPane.INFORMATION_MESSAGE);
                            showCard(CARD_BOOKING);
                        });
                        card.add(Box.createVerticalStrut(4));
                        card.add(book);
                    }
                    resultsPanel.add(card);
                    resultsPanel.add(Box.createVerticalStrut(6));
                }
            }
            resultsPanel.setVisible(true);
            resultsPanel.revalidate();
            resultsPanel.repaint();
        });

        p.add(search);
        p.add(Box.createVerticalStrut(12));
        p.add(resultsPanel);
        p.add(Box.createVerticalStrut(6));
        p.add(makeNote("Γράψε «Άγνωστο» στο πεδίο Περιοχή για εναλλακτική ροή Α1"));
        p.add(Box.createVerticalStrut(8));
        p.add(makeSeparator());
        p.add(makeNavBar("Διαδρομές"));

        return wrapScroll(p);
    }

    // ══════════════════════════════════════════════════════════════════════════
    // SCREEN 5 — BOOKING CONFIRM
    // ══════════════════════════════════════════════════════════════════════════
    static JPanel buildBooking() {
        JPanel p = makeScrollPanel();

        p.add(makeBackButton(CARD_DASH));
        p.add(makeHeader("📋 ΕΚΚΡΕΜΕΙΣ ΚΡΑΤΗΣΕΙΣ", "UC5 — BookingMgmtController"));
        p.add(Box.createVerticalStrut(12));
        p.add(makeAltNote("ⓘ Ο οδηγός βλέπει αυτή την οθόνη μετά από push notification"));
        p.add(Box.createVerticalStrut(12));

        JPanel card = makeCard();
        card.add(new JLabel("Αίτημα κράτησης από:") {{ setFont(SMALL); setForeground(MUTED); }});
        card.add(new JLabel("Αλέξης Δημητρίου") {{ setFont(BOLD_SM); setForeground(FG); }});
        card.add(Box.createVerticalStrut(6));
        card.add(makeRow("Ημερομηνία:", TOMORROW + "  08:15", false));
        card.add(makeRow("Διαδρομή:", "Τρίκαλα → Βιομηχανική Ζώνη", false));
        card.add(makeRow("Θέση:", "1 από 2 διαθέσιμες", false));
        card.add(makeRow("Κατάσταση:", "⏳ pending", false));
        card.add(Box.createVerticalStrut(8));

        JPanel btns = new JPanel(new GridLayout(1, 2, 8, 0));
        btns.setBackground(Color.WHITE);
        JButton accept = makeAccentButton("✓ ΕΠΙΒΕΒΑΙΩΣΗ");
        accept.addActionListener(e -> {
            JOptionPane.showMessageDialog(mainFrame,
                    "✅ Κράτηση επιβεβαιώθηκε!\n(Booking status=confirmed → Push στον Αλέξη)",
                    "Επιβεβαίωση", JOptionPane.INFORMATION_MESSAGE);
            showCard(CARD_DASH);
        });
        JButton reject = makeButton("✗ ΑΠΟΡΡΙΨΗ");
        reject.setForeground(CLR_ERROR);
        reject.setBorder(BorderFactory.createLineBorder(CLR_ERROR, 1));
        reject.addActionListener(e -> {
            JOptionPane.showMessageDialog(mainFrame,
                    "Απόρριψη\n(Booking status=rejected → Push στον Αλέξη)",
                    "Απόρριψη", JOptionPane.WARNING_MESSAGE);
            showCard(CARD_DASH);
        });
        btns.add(accept);
        btns.add(reject);
        card.add(btns);
        p.add(card);
        p.add(Box.createVerticalStrut(12));

        p.add(makeWarnCard("⏱ Auto-cancel",
                "Αν δεν απαντήσεις εντός 2 ωρών, η κράτηση ακυρώνεται αυτόματα."));
        p.add(Box.createVerticalStrut(12));

        p.add(makeSectionLabel("Ακύρωση από Επιβάτη"));
        p.add(Box.createVerticalStrut(6));
        JPanel cancelCard = makeCard();
        cancelCard.add(new JLabel("Κράτηση από Αλέξη Δ.  ·  " + TOMORROW + "  08:15") {{ setFont(SMALL); }});
        JButton cancelBooking = makeButton("ΑΚΥΡΩΣΗ ΚΡΑΤΗΣΗΣ");
        cancelBooking.setForeground(CLR_ERROR);
        cancelBooking.setFont(SMALL);
        cancelBooking.addActionListener(e -> {
            int res = JOptionPane.showConfirmDialog(mainFrame,
                    "Είσαι σίγουρος; Πολιτική ακύρωσης: έως 1 ώρα πριν.",
                    "Επιβεβαίωση Ακύρωσης", JOptionPane.YES_NO_OPTION);
            if (res == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(mainFrame,
                        "(Booking status=cancelled → Push στον οδηγό)",
                        "Ακύρωση", JOptionPane.INFORMATION_MESSAGE);
                showCard(CARD_DASH);
            }
        });
        cancelCard.add(cancelBooking);
        p.add(cancelCard);

        return wrapScroll(p);
    }

    // ══════════════════════════════════════════════════════════════════════════
    // SCREEN 6 — TRIP COMPLETE
    // ══════════════════════════════════════════════════════════════════════════
    static JPanel buildTripComplete() {
        JPanel p = makeScrollPanel();

        p.add(makeBackButton(CARD_DASH));
        p.add(makeHeader("🚗 ΕΝΕΡΓΗ ΜΕΤΑΚΙΝΗΣΗ", "TripCompletionController"));
        p.add(Box.createVerticalStrut(12));

        JPanel tripCard = makeCard();
        tripCard.add(new JLabel("Ενεργή Διαδρομή") {{ setFont(BOLD_SM); setForeground(ACCENT); }});
        tripCard.add(Box.createVerticalStrut(4));
        tripCard.add(makeRow("Εκκίνηση:",     "Τρίκαλα, Καρδίτσης 14", false));
        tripCard.add(makeRow("Προορισμός:",   "Βιομηχανική Ζώνη, Λάρισα", false));
        tripCard.add(makeRow("Επιβάτες:",     "Αλέξης Δ., Σοφία Κ.", false));
        tripCard.add(makeRow("GPS:",          "✅ Ενεργό  (GPSNavigatorController)", false));
        tripCard.add(makeRow("Εκτιμ. άφιξη:","08:53", false));
        p.add(tripCard);
        p.add(Box.createVerticalStrut(12));

        JButton complete = makeAccentButton("🏁 ΟΛΟΚΛΗΡΩΣΗ ΔΙΑΔΡΟΜΗΣ");
        complete.setAlignmentX(Component.CENTER_ALIGNMENT);
        complete.setPreferredSize(new Dimension(280, 40));
        complete.addActionListener(e -> {
            JOptionPane.showMessageDialog(mainFrame,
                    "✅ Διαδρομή ολοκληρώθηκε!\n\n" +
                            "Trip [E]: status=completed  |  42.3 km  |  3.2 kg CO2\n" +
                            "PointsController: +84 pontoi (42 km x 2.0 odigos)\n",
                    "Ολοκλήρωση", JOptionPane.INFORMATION_MESSAGE);
            showCard(CARD_RATING);
        });
        p.add(complete);
        p.add(Box.createVerticalStrut(12));

        p.add(makeWarnCard("⚠ GPS μη διαθέσιμο",
                "Αν το GPS αποτύχει, το σύστημα ζητά χειροκίνητη επιβεβαίωση."));
        p.add(Box.createVerticalStrut(10));

        p.add(makeSectionLabel("Αναφορά Προβλήματος"));
        p.add(Box.createVerticalStrut(6));
        JButton dispute = makeButton("📢 Αναφορά Προβλήματος");
        dispute.setForeground(CLR_ERROR);
        dispute.setAlignmentX(Component.CENTER_ALIGNMENT);
        dispute.addActionListener(e -> {
            String reason = JOptionPane.showInputDialog(mainFrame,
                    "Περιέγραψε το πρόβλημα:", "Αναφορά Προβλήματος", JOptionPane.PLAIN_MESSAGE);
            if (reason != null && !reason.trim().isEmpty()) {
                JOptionPane.showMessageDialog(mainFrame,
                        "(Dispute Request δημιουργήθηκε → admin queue)",
                        "Αναφορά Εστάλη", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        p.add(dispute);

        return wrapScroll(p);
    }

    // ══════════════════════════════════════════════════════════════════════════
    // SCREEN 7 — RATING
    // ══════════════════════════════════════════════════════════════════════════
    static JPanel buildRating() {
        JPanel p = makeScrollPanel();

        p.add(makeBackButton(CARD_DASH));
        p.add(makeHeader("⭐ ΑΞΙΟΛΟΓΗΣΗ", "παράθυρο 48 ωρών"));
        p.add(Box.createVerticalStrut(12));

        JPanel rateInfo = makeCard();
        rateInfo.add(new JLabel("Αξιολόγησε τη μετακίνησή σου:") {{ setFont(SMALL); setForeground(MUTED); }});
        rateInfo.add(new JLabel("Γιώργος Παπαδόπουλος  (Οδηγός)") {{ setFont(BOLD_SM); }});
        rateInfo.add(new JLabel(TOMORROW + "  ·  08:15  ·  42.3 km") {{ setFont(SMALL); setForeground(MUTED); }});
        p.add(rateInfo);
        p.add(Box.createVerticalStrut(12));

        p.add(makeSectionLabel("ΒΑΘΜΟΛΟΓΙΑ (1-5 αστέρια)"));
        p.add(Box.createVerticalStrut(6));
        JPanel starsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        starsPanel.setBackground(BG);
        starsPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        int[] selected = {0};
        JButton[] stars = new JButton[5];
        for (int i = 0; i < 5; i++) {
            final int val = i + 1;
            stars[i] = new JButton("★");
            stars[i].setFont(new Font("SansSerif", Font.BOLD, 20));
            stars[i].setForeground(MUTED);
            stars[i].setBackground(BG);
            stars[i].setBorderPainted(false);
            stars[i].setFocusPainted(false);
            stars[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            stars[i].addActionListener(e -> {
                selected[0] = val;
                for (int j = 0; j < 5; j++)
                    stars[j].setForeground(j < val ? new Color(0xFFA000) : MUTED);
            });
            starsPanel.add(stars[i]);
        }
        p.add(starsPanel);
        p.add(Box.createVerticalStrut(8));

        p.add(makeSectionLabel("ΣΧΟΛΙΟ"));
        p.add(Box.createVerticalStrut(4));
        JTextArea commentArea = new JTextArea(3, 28);
        commentArea.setFont(BODY);
        commentArea.setLineWrap(true);
        commentArea.setWrapStyleWord(true);
        commentArea.setBorder(BorderFactory.createLineBorder(BORDER, 1));
        JScrollPane commentScroll = new JScrollPane(commentArea);
        commentScroll.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));
        p.add(commentScroll);
        p.add(Box.createVerticalStrut(4));
        p.add(makeAltNote("ⓘ αν βαθμολογία ≤2 αστέρια, το σχόλιο είναι υποχρεωτικό."));
        p.add(Box.createVerticalStrut(10));

        JLabel errLbl = makeErrLabel();
        p.add(errLbl);
        p.add(Box.createVerticalStrut(4));

        JButton submit = makeAccentButton("ΥΠΟΒΟΛΗ ΑΞΙΟΛΟΓΗΣΗΣ ✓");
        submit.setAlignmentX(Component.CENTER_ALIGNMENT);
        submit.addActionListener(e -> {
            if (selected[0] == 0) { errLbl.setText("⚠ Επίλεξε τουλάχιστον 1 αστέρι."); return; }
            if (selected[0] <= 2 && commentArea.getText().trim().isEmpty()) {
                commentArea.setBorder(BorderFactory.createLineBorder(CLR_ERROR, 2));
                errLbl.setText("⚠ Για βαθμολογία ≤2 αστέρια, το σχόλιο είναι υποχρεωτικό.");
                return;
            }
            commentArea.setBorder(BorderFactory.createLineBorder(BORDER, 1));
            errLbl.setText(" ");
            JOptionPane.showMessageDialog(mainFrame,
                    "✅ Αξιολόγηση υποβλήθηκε!\n" + selected[0] + " αστέρια",
                    "Αξιολόγηση", JOptionPane.INFORMATION_MESSAGE);
            showCard(CARD_DASH);
        });
        p.add(submit);
        p.add(Box.createVerticalStrut(6));
        JButton skip = makeButton("Παράλειψη");
        skip.setForeground(MUTED);
        skip.setAlignmentX(Component.CENTER_ALIGNMENT);
        skip.addActionListener(e -> showCard(CARD_DASH));
        p.add(skip);
        p.add(Box.createVerticalStrut(6));
        p.add(makeNote("Παράθυρο αξιολόγησης: 48 ώρες από την ολοκλήρωση"));

        return wrapScroll(p);
    }

    // ══════════════════════════════════════════════════════════════════════════
    // SCREEN 8 — POINTS & PRIVILEGES
    // ══════════════════════════════════════════════════════════════════════════
    static JPanel buildPrivileges() {
        JPanel p = makeScrollPanel();

        p.add(makeBackButton(CARD_DASH));
        p.add(makeHeader("🏆 ΠΟΝΤΟΙ & ΠΡΟΝΟΜΙΑ", "PointsController  |  LeaderboardController"));
        p.add(Box.createVerticalStrut(12));

        JPanel card = makeCard();
        JLabel pts = new JLabel(USER_POINTS + " π.", SwingConstants.CENTER);
        pts.setFont(new Font("SansSerif", Font.BOLD, 28));
        pts.setForeground(ACCENT);
        pts.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(pts);
        card.add(Box.createVerticalStrut(4));
        card.add(makeProgressBar(USER_POINTS, 500));
        card.add(Box.createVerticalStrut(4));
        card.add(new JLabel("Επίπεδο 3  |  180π. ακόμα για Επ.4  |  Leaderboard: #4 / 38") {{
            setFont(SMALL); setForeground(MUTED); }});
        card.add(Box.createVerticalStrut(6));
        card.add(new JLabel("Πόντοι: km x 2.0 (οδηγός) ή km x 1.0 (επιβάτης)") {{
            setFont(SMALL); setForeground(MUTED); }});
        p.add(card);
        p.add(Box.createVerticalStrut(12));

        p.add(makeSectionLabel("ΕΠΙΠΕΔΑ ΠΡΟΝΟΜΙΩΝ"));
        p.add(Box.createVerticalStrut(6));
        String[] cols = {"Επ.", "Πόντοι", "Προνόμιο", "Κατάσταση"};
        Object[][] rows = {
                {"1","50π.",  "Green Commuter Badge",  "✅ Ξεκλείδωτο"},
                {"2","150π.", "Προτεραιότητα Parking", "✅ Ξεκλείδωτο"},
                {"3","300π.", "Δωροκάρτα 10€",         "✅ Ξεκλείδωτο"},
                {"4","500π.", "Extra Τηλεργασία",       "🔒 180π. ακόμα"},
                {"5","800π.", "Μειωμένη Ασφάλιση",      "🔒 Κλειδωμένο"},
                {"6","1200π.","Green MVP Trophy",        "🔒 Κλειδωμένο"},
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
        JScrollPane tScroll = new JScrollPane(table);
        tScroll.setPreferredSize(new Dimension(380, 160));
        tScroll.setMaximumSize(new Dimension(Integer.MAX_VALUE, 160));
        p.add(tScroll);
        p.add(Box.createVerticalStrut(12));

        JButton redeem = makeAccentButton("ΕΞΑΡΓΥΡΩΣΗ ΠΡΟΝΟΜΙΟΥ");
        redeem.setAlignmentX(Component.CENTER_ALIGNMENT);
        redeem.addActionListener(e -> {
            int res = JOptionPane.showOptionDialog(mainFrame,
                    "Επίλεξε προνόμιο για εξαργύρωση:",
                    "Εξαργύρωση — RedeemController",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                    new String[]{"Δωροκάρτα 10€ (300π.)", "Extra Τηλεργασία (500π.) — Εναλλ. Α2"}, null);
            if (res == 0) {
                JOptionPane.showMessageDialog(mainFrame,
                        "✅ Εξαργύρωση επιτυχής!\n(Points deducted: -300π.  |  Coupon created)",
                        "UC9a — Επιτυχία", JOptionPane.INFORMATION_MESSAGE);
            } else if (res == 1) {
                JOptionPane.showMessageDialog(mainFrame,
                        "⚠ Ανεπαρκείς Πόντοι\nΈχεις: " + USER_POINTS + "π.  |  Απαιτούνται: 500π.\nΧρειάζεσαι ακόμα: 180π.",
                        "Ανεπαρκείς Πόντοι", JOptionPane.WARNING_MESSAGE);
            }
        });
        p.add(redeem);
        p.add(Box.createVerticalStrut(8));
        p.add(makeNote("Πόντοι: km x 2.0 οδηγός  |  km x 1.0 επιβάτης"));
        p.add(Box.createVerticalStrut(8));
        p.add(makeSeparator());
        p.add(makeNavBar("Πόντοι"));

        return wrapScroll(p);
    }

    // ══════════════════════════════════════════════════════════════════════════
    // SCREEN 9 — PROFILE
    // ══════════════════════════════════════════════════════════════════════════
    static JPanel buildProfile() {
        JPanel p = makeScrollPanel();

        p.add(makeBackButton(CARD_DASH));
        p.add(makeHeader("👤 ΠΡΟΦΙΛ", "ProfileController → Employee"));
        p.add(Box.createVerticalStrut(12));

        JTextField nameField  = new JTextField(USER_NAME, 18);
        JTextField emailField = new JTextField(USER_EMAIL, 18);
        emailField.setEditable(false);
        emailField.setForeground(MUTED);
        JTextField addrField  = new JTextField("Τρίκαλα, Καρδίτσης 14", 18);
        JTextField schedField = new JTextField("08:00 - 17:00", 18);
        JComboBox<String> roleBox = new JComboBox<>(new String[]{"Επιβάτης", "Οδηγός", "Οδηγός & Επιβάτης"});
        roleBox.setSelectedIndex(2);
        styleCombo(roleBox);

        p.add(makeSectionLabel("ΣΤΟΙΧΕΙΑ ΛΟΓΑΡΙΑΣΜΟΥ"));
        p.add(Box.createVerticalStrut(6));
        p.add(makeFieldRow("Ονοματεπώνυμο:", nameField));
        p.add(Box.createVerticalStrut(6));
        p.add(makeFieldRow("Email (σταθερό):", emailField));
        p.add(Box.createVerticalStrut(4));
        p.add(makeAltNote("ⓘ Το email δεν μπορεί να αλλαχθεί μετά την εγγραφή."));
        p.add(Box.createVerticalStrut(6));
        p.add(makeFieldRow("Διεύθυνση:", addrField));
        p.add(Box.createVerticalStrut(6));
        p.add(makeFieldRow("Ωράριο:", schedField));
        p.add(Box.createVerticalStrut(6));
        p.add(makeFieldRow("Ρόλος:", roleBox));
        p.add(Box.createVerticalStrut(12));

        p.add(makeSectionLabel("ΡΥΘΜΙΣΕΙΣ ΕΙΔΟΠΟΙΗΣΕΩΝ"));
        p.add(Box.createVerticalStrut(6));
        JPanel notifCard = makeCard();
        JCheckBox emailNotif = new JCheckBox("Email ειδοποιήσεις", true);
        JCheckBox pushNotif  = new JCheckBox("Push notifications", true);
        emailNotif.setFont(SMALL); emailNotif.setBackground(Color.WHITE);
        pushNotif.setFont(SMALL);  pushNotif.setBackground(Color.WHITE);
        notifCard.add(emailNotif);
        notifCard.add(pushNotif);
        p.add(notifCard);
        p.add(Box.createVerticalStrut(12));

        JLabel errLbl = makeErrLabel();
        p.add(errLbl);

        JButton save = makeAccentButton("ΑΠΟΘΗΚΕΥΣΗ ΑΛΛΑΓΩΝ");
        save.setAlignmentX(Component.CENTER_ALIGNMENT);
        save.addActionListener(e -> {
            if (nameField.getText().trim().isEmpty()) {
                highlightError(nameField);
                errLbl.setText("⚠ Το ονοματεπώνυμο δεν μπορεί να είναι κενό.");
                return;
            }
            resetField(nameField);
            errLbl.setText(" ");
            JOptionPane.showMessageDialog(mainFrame,
                    "✅ Αλλαγές αποθηκεύτηκαν!\n(Employee updated: " + nameField.getText().trim() + ")",
                    "UC10 — Αποθήκευση", JOptionPane.INFORMATION_MESSAGE);
        });
        p.add(save);
        p.add(Box.createVerticalStrut(8));

        JButton changePwd = makeButton("🔐 Αλλαγή Κωδικού");
        changePwd.setForeground(ACCENT);
        changePwd.setAlignmentX(Component.CENTER_ALIGNMENT);
        changePwd.addActionListener(e -> {
            JPanel pwdPanel = new JPanel(new GridLayout(3, 2, 6, 6));
            JPasswordField curr = new JPasswordField(15);
            JPasswordField newP = new JPasswordField(15);
            JPasswordField conf = new JPasswordField(15);
            pwdPanel.add(new JLabel("Τρέχων κωδικός:")); pwdPanel.add(curr);
            pwdPanel.add(new JLabel("Νέος κωδικός:"));   pwdPanel.add(newP);
            pwdPanel.add(new JLabel("Επιβεβαίωση:"));    pwdPanel.add(conf);
            int res = JOptionPane.showConfirmDialog(mainFrame, pwdPanel,
                    "Αλλαγή Κωδικού", JOptionPane.OK_CANCEL_OPTION);
            if (res == JOptionPane.OK_OPTION) {
                if (new String(newP.getPassword()).length() < 8) {
                    JOptionPane.showMessageDialog(mainFrame,
                            "⚠ Ο κωδικός πρέπει να έχει τουλάχιστον 8 χαρακτήρες.",
                            "Αδύναμος Κωδικός", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                JOptionPane.showMessageDialog(mainFrame,
                        "✅ Κωδικός ενημερώθηκε! Απαιτείται επανασύνδεση.",
                        "Επιτυχία", JOptionPane.INFORMATION_MESSAGE);
                showCard(CARD_LOGIN);
            }
        });
        p.add(changePwd);
        p.add(Box.createVerticalStrut(8));
        p.add(makeSeparator());
        p.add(makeNavBar("Προφίλ"));

        return wrapScroll(p);
    }

    // ══════════════════════════════════════════════════════════════════════════
    // UI HELPERS
    // ══════════════════════════════════════════════════════════════════════════

    //βοηθητικες μεθοδοι για να δημιουργηθουν τα UI components (χρηση για αποφυγη επανληψης κωδικα)
    static JPanel makeScrollPanel() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(BG);
        p.setBorder(BorderFactory.createEmptyBorder(20, 24, 20, 24));
        return p;
    }


    static JPanel wrapScroll(JPanel inner) {
        JScrollPane scroll = new JScrollPane(inner);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(12);
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.add(scroll, BorderLayout.CENTER);
        return wrapper;
    }

    static JButton makeBackButton(String targetCard) {
        JButton b = makeButton("← Πίσω");
        b.setForeground(ACCENT);
        b.addActionListener(e -> showCard(targetCard));
        return b;
    }

    static JLabel makeErrLabel() {
        JLabel l = new JLabel(" ");
        l.setFont(SMALL);
        l.setForeground(CLR_ERROR);
        l.setAlignmentX(Component.CENTER_ALIGNMENT);
        return l;
    }

    static JPanel makeHeader(String title, String subtitle) {
        JPanel h = new JPanel();
        h.setLayout(new BoxLayout(h, BoxLayout.Y_AXIS));
        h.setBackground(SUBTLE);
        h.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 4, 0, 0, ACCENT),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)));
        h.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        JLabel t = new JLabel(title); t.setFont(TITLE); t.setForeground(ACCENT);
        JLabel s = new JLabel(subtitle); s.setFont(SMALL); s.setForeground(MUTED);
        h.add(t); h.add(s);
        return h;
    }

    static JPanel makeCard() {
        JPanel c = new JPanel();
        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
        c.setBackground(Color.WHITE);
        c.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER, 1),
                BorderFactory.createEmptyBorder(10, 14, 10, 14)));
        c.setMaximumSize(new Dimension(Integer.MAX_VALUE, 300));
        return c;
    }

    static JPanel makeClickableCard(String label, Runnable onClick) {
        JPanel c = makeCard();
        JLabel lbl = new JLabel("▶ " + label);
        lbl.setFont(BOLD_SM); lbl.setForeground(INFO_FG);
        c.add(lbl);
        c.add(Box.createVerticalStrut(4));
        c.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        c.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) { onClick.run(); }
            public void mouseEntered(MouseEvent e) { c.setBackground(new Color(0xF0F8FF)); }
            public void mouseExited(MouseEvent e)  { c.setBackground(Color.WHITE); }
        });
        return c;
    }

    static JPanel makeWarnCard(String title, String body) {
        JPanel c = new JPanel();
        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
        c.setBackground(WARN_BG);
        c.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 4, 0, 0, new Color(0xFFA000)),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)));
        c.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        c.add(new JLabel(title) {{ setFont(BOLD_SM); setForeground(WARN_FG); }});
        c.add(new JLabel("<html>" + body.replace("\n", "<br>") + "</html>") {{ setFont(SMALL); setForeground(WARN_FG); }});
        return c;
    }

    static JPanel makeErrorCard(String title, String body) {
        JPanel c = new JPanel();
        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
        c.setBackground(ERROR_BG);
        c.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 4, 0, 0, CLR_ERROR),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)));
        c.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));
        c.add(new JLabel(title) {{ setFont(BOLD_SM); setForeground(CLR_ERROR); }});
        c.add(new JLabel(body)  {{ setFont(SMALL);   setForeground(CLR_ERROR); }});
        return c;
    }

    static JLabel makeAltNote(String text) {
        JLabel l = new JLabel(text);
        l.setFont(SMALL); l.setForeground(INFO_FG);
        l.setBackground(INFO_BG); l.setOpaque(true);
        l.setBorder(BorderFactory.createEmptyBorder(3, 8, 3, 8));
        return l;
    }

    static JPanel makeFieldRow(String label, JComponent field) {
        JPanel row = new JPanel(new BorderLayout(8, 0));
        row.setBackground(BG);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        JLabel lbl = new JLabel(label);
        lbl.setFont(BODY);
        lbl.setPreferredSize(new Dimension(140, 24));
        if (field instanceof JTextField) ((JTextField) field).setFont(BODY);
        row.add(lbl, BorderLayout.WEST);
        row.add(field, BorderLayout.CENTER);
        return row;
    }

    static JPanel makeRow(String label, String value, boolean bold) {
        JPanel row = new JPanel(new BorderLayout());
        row.setBackground(Color.WHITE);
        JLabel l = new JLabel(label); l.setFont(SMALL);
        JLabel v = new JLabel(value);
        v.setFont(bold ? BOLD_SM : SMALL);
        v.setForeground(bold ? ACCENT : FG);
        row.add(l, BorderLayout.WEST);
        row.add(v, BorderLayout.EAST);
        return row;
    }

    static JButton makeButton(String text) {
        JButton b = new JButton(text);
        b.setFont(BODY); b.setBackground(BG); b.setForeground(FG);
        b.setFocusPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return b;
    }

    static JButton makeAccentButton(String text) {
        JButton b = new JButton(text);
        b.setFont(BOLD_SM); b.setBackground(ACCENT); b.setForeground(Color.WHITE);
        b.setOpaque(true); b.setBorderPainted(false); b.setFocusPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setPreferredSize(new Dimension(200, 36));
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
// για απεικονιση ποντων
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
        bar.setPreferredSize(new Dimension(300, 10));
        bar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 10));
        return bar;
    }
 // navigation bar στο κατω μερος του ui για γρηγορη μεταβαση μεταξυ οθονων
    static JPanel makeNavBar(String active) {
        String[] items   = {"🏠 Home", "🚗 Διαδρομές", "🏆 Πόντοι", "👤 Προφίλ"};
        String[] targets = {CARD_DASH, CARD_SEARCH, CARD_POINTS, CARD_PROFILE};
        JPanel nav = new JPanel(new GridLayout(1, 4));
        nav.setBackground(BG);
        nav.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, BORDER));
        nav.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
        for (int i = 0; i < items.length; i++) {
            final String target = targets[i];
            JButton b = new JButton(items[i]);
            b.setFont(new Font("SansSerif", Font.PLAIN, 9));
            b.setBorderPainted(false); b.setFocusPainted(false);
            boolean isActive = items[i].contains(active);
            b.setBackground(isActive ? SUBTLE : BG);
            b.setForeground(isActive ? ACCENT : MUTED);
            b.addActionListener(e -> showCard(target));
            nav.add(b);
        }
        return nav;
    }

    static void highlightError(JComponent c) {
        c.setBorder(BorderFactory.createLineBorder(CLR_ERROR, 2));
    }
//για επαναφορα πεδιου στην αρχικη μορφη
    static void resetField(JComponent c) {
        if (c instanceof JTextField)
            c.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
        else
            c.setBorder(BorderFactory.createLineBorder(BORDER, 1));
    }

    static void styleCombo(JComboBox<?> c) {
        c.setFont(BODY); c.setBackground(Color.WHITE);
    }
}