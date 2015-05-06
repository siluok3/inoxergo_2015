package mail;

import com.sun.mail.smtp.SMTPAddressFailedException;
import com.sun.mail.smtp.SMTPAddressSucceededException;
import com.sun.mail.smtp.SMTPSendFailedException;
import com.sun.mail.smtp.SMTPTransport;
import org.apache.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Properties;

/**
 * Demo app that shows how to construct and send an RFC822
 * (singlepart) message.
 * <p>
 * XXX - allow more than one recipient on the command line
 * <p>
 *@param  -To        the email thet we use to receive a message from a user
 *@string  to        if the previous argv is -To, takes the value of -To
 *@param   -s        the subject of the email message
 *@string  subject   if the previous argv is -s, takes the value of -s
 *@param   -bo       the body of the email message
 *@string  body      if the previous argv is -bo, takes the value of -bo
 *@param   -o        the email of the user
 *@string  from      if the previous argv is -o, takes the value of -o
 *@param   -c        the email of the user in order to send the message back to him/her
 *@string  cc        if the previous argv is -c, takes the value of -c
 *@param   -b        the email of others in order to send the message  to them
 *@string  bcc       if the previous argv is -b, takes the value of -b
 *@param   -L        a url given
 *@string  url       if the previous argv is -L, takes the value of -L
 *@param   -M        the host computer
 *@string  mailhost  if the previous argv is -M, takes the value of -M
 *@string  mailer    default value that is shown in the details of an email, gives info for X-mailer
 *@param   -T        the protocol used for the email
 *@string  protocol  if the previous argv is -T, takes the value of -T
 *@param   -H        the host of the email service, us
 *@string  host      if the previous argv is -H, takes the value of -H
 *@param   -U        the user
 *@string  user      if the previous argv is -U, takes the value of -U
 *@param   -P        the password of the user
 *@string  password  if the previous argv is -P, takes the value of the -P
 *@param   -f        name of folder in which to record mail
 *@string  record    if the previous argv is -f, takes the value of the -f
 *@param   -d        used for info messages
 *@boolean debug     if the previous argv is -d, takes the value of the -d
 *@param   -v
 *@boolean verbose   if the previous argv is -v, takes the value of the -v
 *@param   -a        used for authenticating the user id
 *@boolean auth      if the previous argv is -a, takes the value of the -a
 *@param   --        optind++, goes to the next args
 *@param   -         contains and shows Usage details
 *  <p>
 * This is just a variant of msgsend.java that demonstrates use of
 * <p>
 * @author Max Spivak
 * @author Bill Shannon
 */

public class smtpsend {
    static Logger cat = org.apache.log4j.Logger.getLogger("Database.class");

    public void send(String[] argv) {

        String to = null, subject = null, body = null, from = null,
                cc = null, bcc = null, url = null;
        String mailhost = null;
        String mailer = "kalypsoshop.gr miniSMTP v.1.0.1";
        String protocol = null, host = null, user = null, password = null;
        String record = null;    // name of folder in which to record mail
        boolean debug = false;
        boolean verbose = false;
        boolean auth = false;
        BufferedReader in =
                new BufferedReader(new InputStreamReader(System.in));
        int optind;

        for (optind = 0; optind < argv.length; optind++) {
            if (argv[optind].equals("-To")) {    
                to = argv[++optind];
            } else if (argv[optind].equals("-T")) {
                protocol = argv[++optind];
            } else if (argv[optind].equals("-H")) {
                host = argv[++optind];
            } else if (argv[optind].equals("-U")) {
                user = argv[++optind];
            } else if (argv[optind].equals("-P")) {
                if (!argv[++optind].equals(""))           // if  -P = "" dont use it
                    password = argv[++optind];
            } else if (argv[optind].equals("-M")) {    //an args{x}="-M", to epomeno args einai o host ypologostis
                mailhost = argv[++optind];
            } else if (argv[optind].equals("-f")) {
                record = argv[++optind];
            } else if (argv[optind].equals("-s")) {
                subject = argv[++optind];
            } else if (argv[optind].equals("-bo")) {
                body = argv[++optind];
            } else if (argv[optind].equals("-o")) { // originator
                from = argv[++optind];
            } else if (argv[optind].equals("-c")) {
                cc = argv[++optind];
            } else if (argv[optind].equals("-b")) {
                bcc = argv[++optind];
            } else if (argv[optind].equals("-L")) {
                url = argv[++optind];
            } else if (argv[optind].equals("-d")) {
                debug = true;
            } else if (argv[optind].equals("-v")) {
                verbose = true;
            } else if (argv[optind].equals("-a")) {
                auth = false;
                //auth = true;
            } else if (argv[optind].equals("--")) {
                optind++;
                break;
            } else if (argv[optind].startsWith("-")) {
                cat.info(
                        "Usage: smtpsend [[-L store-url] | [-T prot] [-H host] [-U user] [-P passwd]]");
                System.out.println(
                        "\t[-s subject] [-o from-address] [-c cc-addresses] [-b bcc-addresses]");
                cat.info(
                        "\t[-f record-mailbox] [-M transport-host] [-d] [-v] [-a] [address]");
                System.exit(1);
            } else {
                break;
            }
        }

        try {
            cat.info("to+user+password+mailhost+subject+from+cc=");
            cat.info(to + user + password + mailhost + subject + from + cc);
            if (to == null) {
                // XXX - concatenate all remaining arguments
                to = argv[optind];
                cat.info("To: " + to);
            } else {
                cat.info("To: " + to);
                //System.out.flush();
                //to = in.readLine();
            }
            if (subject == null) {
                cat.info("Subject: ");
                System.out.flush();
                subject = in.readLine();
            } else {
                System.out.println("Subject: " + subject);
                cat.info("Subject: " + subject);
            }

            Properties props = System.getProperties();
            if (mailhost != null)
                props.put("mail.smtp.host", mailhost);
            System.out.println("mailhost: " + mailhost);
            cat.info("mailhost: " + mailhost);
            if (auth)
                props.put("mail.smtp.auth", "true");
            System.out.println("auth: " + auth);
            cat.info("auth: " + auth);
            // Get a Session object
            Session session = Session.getInstance(props, null);
            if (debug)
                session.setDebug(true);

            // construct the message
            javax.mail.internet.MimeMessage msg = new javax.mail.internet.MimeMessage(session);
            System.out.println("new MimeMessage ");
            cat.info("new MimeMessage ");
            System.out.println("Content type" + msg.getContentType());
            cat.info("Content type" + msg.getContentType());
            if (from != null) {
                InternetAddress ifrom = new InternetAddress();
                ifrom.setAddress(from);
                ifrom.setPersonal("kalypsoshop.gr Mail Deamon ");
                msg.setFrom(ifrom);
            } else
                msg.setFrom();
            System.out.println("from: " + from);
            cat.info("from: " + from);
            //msg.addHeaderLine("HeaderLine");
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to, false));
            if (cc != null)
                msg.setRecipients(Message.RecipientType.CC,
                        InternetAddress.parse(cc, false));
            if (bcc != null)
                msg.setRecipients(Message.RecipientType.BCC,
                        InternetAddress.parse(bcc, false));

            msg.setSubject(subject, "UTF-8");
            System.out.println("subject: " + subject);
            cat.info("subject: " + subject);
            //todo the body
            msg.setText(body, "UTF-8");
            //msg.setText(body);
            //  collect(in, msg);

            msg.setHeader("X-Mailer", mailer);
            System.out.println("X-Mailer: " + mailer);
            cat.info("X-Mailer: " + mailer);
            msg.setSentDate(new Date());

            // send the thing off
            /*
            * The simple way to send a message is this:
            *
           Transport.send(msg);
            *
            * But we're going to use some SMTP-specific features for
            * demonstration purposes so we need to manage the Transport
            * object explicitly.
            */
            SMTPTransport t = (SMTPTransport) session.getTransport("smtp");
            System.out.println("SMTPTransport ");
            cat.info("SMTPTransport ");
            try {
                if (auth)
                    t.connect(mailhost, user, password);
                else
                    t.connect();
                t.sendMessage(msg, msg.getAllRecipients());
            } finally {
                if (verbose)
                    cat.info("Response: " +
                            t.getLastServerResponse());
                t.close();
            }

            System.out.println("\nMail was sent successfully.");
            cat.info("\nMail was sent successfully.");

            // Keep a copy, if requested.

            if (record != null) {
                // Get a Store object
                Store store = null;
                if (url != null) {
                    URLName urln = new URLName(url);
                    store = session.getStore(urln);
                    store.connect();
                } else {
                    if (protocol != null)
                        store = session.getStore(protocol);
                    else
                        store = session.getStore();

                    // Connect
                    if (host != null || user != null || password != null)
                        store.connect(host, user, password);
                    else
                        store.connect();
                }

                // Get record Folder.  Create if it does not exist.
                Folder folder = store.getFolder(record);
                if (folder == null) {
                    System.err.println("Can't get record folder.");
                    cat.error("Can't get record folder.");
                    System.exit(1);
                }
                if (!folder.exists())
                    folder.create(Folder.HOLDS_MESSAGES);

                Message[] msgs = new Message[1];
                msgs[0] = msg;
                folder.appendMessages(msgs);

                System.out.println("Mail was recorded successfully.");
            }

        } catch (Exception e) {
            if (e instanceof SendFailedException) {
                MessagingException sfe = (MessagingException) e;
                if (sfe instanceof SMTPSendFailedException) {
                    SMTPSendFailedException ssfe =
                            (SMTPSendFailedException) sfe;
                    System.out.println("SMTP SEND FAILED:");
                    cat.error("SMTP SEND FAILED:");
                    if (verbose)
                        System.out.println(ssfe.toString());
                    cat.error(ssfe.toString());
                    cat.error("  Command: " + ssfe.getCommand());
                    cat.error("  RetCode: " + ssfe.getReturnCode());
                    cat.error("  Response: " + ssfe.getMessage());
                } else {
                    if (verbose)
                        System.out.println("Send failed: " + sfe.toString());
                    cat.error("Send failed: " + sfe.toString());
                }
                Exception ne;
                while ((ne = sfe.getNextException()) != null &&
                        ne instanceof MessagingException) {
                    sfe = (MessagingException) ne;
                    if (sfe instanceof SMTPAddressFailedException) {
                        SMTPAddressFailedException ssfe =
                                (SMTPAddressFailedException) sfe;
                        cat.error("ADDRESS FAILED:");
                        if (verbose)
                            System.out.println(ssfe.toString());
                        cat.error(ssfe.toString());
                        cat.error("  Address: " + ssfe.getAddress());
                        cat.error("  Command: " + ssfe.getCommand());
                        cat.error("  RetCode: " + ssfe.getReturnCode());
                        cat.error("  Response: " + ssfe.getMessage());
                    } else if (sfe instanceof SMTPAddressSucceededException) {
                        cat.info("ADDRESS SUCCEEDED:");
                        SMTPAddressSucceededException ssfe =
                                (SMTPAddressSucceededException) sfe;
                        if (verbose)
                            System.out.println(ssfe.toString());
                        cat.info("  Address: " + ssfe.getAddress());
                        cat.info("  Command: " + ssfe.getCommand());
                        cat.info("  RetCode: " + ssfe.getReturnCode());
                        cat.info("  Response: " + ssfe.getMessage());
                    }
                }
            } else {
                System.out.println("Got Exception: " + e);
                cat.error("Got Exception: " + e);
                if (verbose)
                    e.printStackTrace();

            }
        }
    }

    public static void collect(BufferedReader in, Message msg)
            throws MessagingException, IOException {
        String line;
        //cat.info("  Address: " + ssfe.getAddress());
        StringBuffer sb = new StringBuffer();
        while ((line = in.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }

        // If the desired charset is known, you can use
        // setText(text, charset)
        msg.setText(sb.toString());
    }
}
