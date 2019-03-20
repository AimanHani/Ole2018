package backgroundTask;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SMSVerification {
    // Find your Account Sid and Auth Token at twilio.com/console
    public static final String ACCOUNT_SID =
            "AC449ad5037d5a1fde39bbaeb0a6b88471";
    public static final String AUTH_TOKEN =
            "c959c20f1df858a8c5db4bedda50cc92";

    public static boolean send(String to,String verificationCode){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message
                .creator( new com.twilio.type.PhoneNumber(to), // to
                         new com.twilio.type.PhoneNumber("+12016902967"), // from
                        "Your Ole Verification Code: "+verificationCode)
                .create();

        System.out.println(message.getSid());
        return true;
    }
}