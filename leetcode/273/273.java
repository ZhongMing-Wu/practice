class Solution {
    public String numberToWords(int num) {
        if(num == 0) {
            return "Zero";
        }
        int billionVal = num / 1000000000;
        int millionVal = (num % 1000000000) / 1000000;
        int thousandval = (num % 1000000) / 1000;
        int hundredVal = num % 1000;

        StringBuilder sb = new StringBuilder();
        
        if(billionVal != 0) {
            sb.append(handle(billionVal)).append(" Billion");
        }


        if(millionVal != 0) {
            if(sb.length() != 0) {
                sb.append(" ");
            }
            sb.append(handle(millionVal)).append(" Million");
        }

        if(thousandval != 0) {
            if(sb.length() != 0) {
                sb.append(" ");
            }
            sb.append(handle(thousandval)).append(" Thousand");
        }

        if(hundredVal != 0) {
            if(sb.length() != 0) {
                sb.append(" ");
            }
            sb.append(handle(hundredVal));
        }
        return sb.toString();
    }

    public String handle(int val) {
        StringBuilder sb = new StringBuilder();

        // 处理百位
        int tempVal = val / 100;
        if(tempVal != 0) {
            sb.append(one(tempVal)).append(" Hundred");
        }

        tempVal = val % 100;
        // 处理十位和个位
        if(tempVal == 0) {
            return sb.toString();
        }

        if(sb.length() != 0) {
            sb.append(" ");
        }

        if(tempVal < 10) {
            sb.append(one(tempVal));
        } else if(tempVal < 20) {
            sb.append(belowTwenty(tempVal));
        } else {
            sb.append(ten(tempVal / 10));
            if((tempVal % 10) != 0) {
                sb.append(" ").append(one(tempVal % 10));
            }
        }
        return sb.toString();
    }

    public String one(int val) {
        switch(val) {
            case 1: return "One";
            case 2: return "Two";
            case 3: return "Three";
            case 4: return "Four";
            case 5: return "Five";
            case 6: return "Six";
            case 7: return "Seven";
            case 8: return "Eight";
            case 9: return "Nine";
            default: return "Zero";
        }
    }

    public String belowTwenty(int val) {
        switch(val) {
            case 10: return "Ten";
            case 11: return "Eleven";
            case 12: return "Twelve";
            case 13: return "Thirteen";
            case 14: return "Fourteen";
            case 15: return "Fifteen";
            case 16: return "Sixteen";
            case 17: return "Seventeen";
            case 18: return "Eighteen";
            default: return "Nineteen";
        }
    }

    public String ten(int val) {
        switch(val) {
            case 2: return "Twenty";
            case 3: return "Thirty";
            case 4: return "Forty";
            case 5: return "Fifty";
            case 6: return "Sixty";
            case 7: return "Seventy";
            case 8: return "Eighty";
            default: return "Ninety";
        }
    }
}