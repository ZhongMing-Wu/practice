import java.util.regex.Pattern;
class Solution {
    String chunkIPv4 = "([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])";
        Pattern patternIPv4 = Pattern.compile("^(" + chunkIPv4 + ".){3}" + chunkIPv4 + "$");
        String chunkIPv6 = "([0-9a-fA-F]{1,4})";
        Pattern patternIPv6 = Pattern.compile("^(" + chunkIPv6 + ":){7}" + chunkIPv6 + "$");
    public String validIPAddress(String IP) {
        if(IP.contains(".")) {
            return patternIPv4.matcher(IP).matches() ? "IPv4" : "Neither";
        }

        if(IP.contains(":")) {
            return patternIPv6.matcher(IP).matches() ? "IPv6" : "Neither";
        }
        return "Neither";
    }
}