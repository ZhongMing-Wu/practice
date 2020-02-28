class Solution {
public:
	void trim(string &s){
		if (!s.empty()){
			s.erase(0, s.find_first_not_of(" "));
			s.erase(s.find_last_not_of(" ") + 1);
		}		
	}

	bool isNumber(string s) {
		int state = 0;
		trim(s);
		for (int i = 0; i < s.size(); i++) {
			switch (s[i]) {
			case '+':
			case '-':
				if (state == 0)
					state = 1;
				else if (state == 4)
					state = 6;
				else
					return false;
				break;
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
			case '0':
				switch (state) {
				case 0:
				case 1:
				case 2:
					state = 2; break;
				case 3:
					state = 3; break;
				case 4:
				case 5:
				case 6:
					state = 5; break;
				case 7:
				case 8:
					state = 8; break;
				default: 
					return false;
				}
				break;
			case '.':
				if (state == 0 || state == 1)
					state = 7;
				else if (state == 2)
					state = 3;
				else
					return false;
				break;
			case 'e':
				if (state == 2 || state == 3 || state == 8)
					state = 4;
				else
					return false;
				break;
            default:
                return false;
			}
		}
		return state == 2 || state == 3 || state == 5 || state == 8;
	}
};