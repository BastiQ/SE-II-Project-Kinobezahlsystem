package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.barzahlung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class BarzahlungsWerkzeug
{
    private BarzahlungsWerkzeugUI barUI;
    private int preis = 0;
    private boolean _success = false;

    /**
     * Konstruktor für das Barzahlungswerkzeug. Erstellt neues UI Fenster, übergibt den Barbetrag 
     * und initialiesiert die Listener
     *
     * @param preis
     */
    public BarzahlungsWerkzeug(int preis)
    {
        this.preis = preis;
        barUI = new BarzahlungsWerkzeugUI();
        barUI.getBetragFeld()
            .setText(preis + "");
        barUI.getBezahltFeld()
            .getDocument()
            .addDocumentListener(new DocumentListener()
            {
                public void changedUpdate(DocumentEvent e)
                {
                    changedBetragTextFeld();
                }

                public void removeUpdate(DocumentEvent e)
                {
                    changedBetragTextFeld();
                }

                public void insertUpdate(DocumentEvent e)
                {
                    changedBetragTextFeld();
                }
            });

        barUI.getAbbrechenButton()
            .addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    _success = false;
                    barUI.getJd()
                        .dispose();
                }
            });

        barUI.getOkButton()
            .addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    _success = true;
                    barUI.getJd()
                        .dispose();
                }
            });

    }

    /**
     * Überprüft, ob der eingegebene Betrag größer oder gleich dem 
     * Barbetrag ist, kein String und nicht negativ ist.
     */
    public void changedBetragTextFeld()
    {
        barUI.getOkButton()
            .setEnabled(false);
        try
        {
            int eingabebetrag = Integer.parseInt(barUI.getBezahltFeld()
                .getText());
            if (eingabebetrag <= 0)
            {
                //JOptionPane.showMessageDialog(null, "Error: Please enter number bigger than 0", "Error Massage", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                int restbetrag = preis - eingabebetrag;
                barUI.getRestbetragFeld()
                    .setText(restbetrag + "");
                if (restbetrag <= 0)
                {
                    barUI.getOkButton()
                        .setEnabled(true);
                }
            }
        }
        catch (NumberFormatException e)
        {
            // keine Zahl sondern int wurde eingegeben
        }
    }

    /*
     * Ausgelagerte get-Methoden
     */
    public void uiZeigen()
    {
        barUI.uiZeigen();
    }

    public boolean getSuccess()
    {
        return _success;
    }

    public JButton getOkButton()
    {
        return barUI.getOkButton();
    }

    public JDialog getJd()
    {
        return barUI.getJd();
    }
}
