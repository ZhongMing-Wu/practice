class Solution {
    public String validIPAddress(String IP) {
        int len = IP.length();
        if(len == 0 || IP.charAt(len - 1) == '.' || IP.charAt(len - 1) == ':') {
            return "Neither";
        }
        String[] elements = IP.split("[:.]", -1);
        if(elements == null || elements.length != 4 && elements.length != 8) {
            return "Neither";
        }

        for(int i = 0; i < elements.length; i++) {
            if(elements[i].equals("")) {
                return "Neither";
            }
        }

        if(elements.length == 4 && checkIPv4(elements)) {
            return "IPv4";
        } 
        if(elements.length == 8 && checkIPv6(elements)) {
            return "IPv6";
        }
        return "Neither";
    }

    public boolean checkIPv4(String[] elements) {
        for(String element : elements) {
            if(element.length() > 4) {
                return false;
            }
            if(element.charAt(0) == '0' && element.length() > 1) {
                return false;
            }
            for(int i = 0; i < element.length(); i++) {
                char c = element.charAt(i);
                if(!Character.isDigit(c)) {
                    return false;
                }
            }
            int val = Integer.valueOf(element);
            if(val > 255) {
                return false;
            }
        }
        return true;
    }

    public boolean checkIPv6(String[] elements) {
        for(String element : elements) {
            if(element.length() > 4) {
                return  false;
            }
            for(int i = 0; i < element.length(); i++) {
                char c = element.charAt(i);
                if(Character.isDigit(c) || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F')) {
                    continue;
                }
                return false;
            }
        }
        return true;
    }
}