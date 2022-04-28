package execution;

import models.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class SendShellCommand {

    private String prof;

    public SendShellCommand() {

    }

    public void executeCommandList(List<User> list) {

        Process process;
        try {
            for (User element : list) {
                if (element.getProfile() == 0) {
                    prof = "SLA-REDIRECT";
                } else if (element.getProfile() == 1) {
                    prof = "SLA-ALLOW-500M";
                } else {
                    prof = "SLA-ALLOW-500M";
                }
                String command = "echo Alc-SLA-Prof-Str=" + prof + ",Acct-Session-Id=" + element.getSessionId() + " | radclient -d /usr/share/freeradius/ -x  10.0.0.245:3799  -r 1 -t 2 coa nokiabng";
                String[] commands = {"bash", "-c", command};
                //Start.sysLogger.toLogInfo("Executing: " + command);
                process = Runtime.getRuntime().exec(commands);
                process.waitFor();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                //Start.sysLogger.toLogInfo("Receiving: ");
                while ((line = bufferedReader.readLine()) != null) {
                    Start.sysLogger.toLogInfo(line);
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }


    }
}

//echo Alc-SLA-Prof-Str = SLA-DENY,Acct-Session-Id=FFDF1B000000BF5EF4B8D3 | radclient -d /usr/share/freeradius/ -x  10.0.0.245:3799  -r 1 -t 2 coa nokiabng