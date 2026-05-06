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
