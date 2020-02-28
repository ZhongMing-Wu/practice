class Solution {
public:
	vector<string> generateParenthesis(int n) {
		string oneAns;
		generate(n, n, oneAns);
		return ans;
	}
	void generate(int leftParenthesesNum, int rightParenthesesNum, string & oneAns) {
		if (rightParenthesesNum == 0) {
			ans.push_back(oneAns);
			return;
		}
		else if (leftParenthesesNum == rightParenthesesNum) {
			oneAns.push_back('(');
			generate(leftParenthesesNum - 1, rightParenthesesNum, oneAns);
			oneAns.pop_back();
			return;
		}
		else {
			if (leftParenthesesNum != 0) {
				oneAns.push_back('(');
				generate(leftParenthesesNum - 1, rightParenthesesNum, oneAns);
				oneAns.pop_back();
			}
			if (leftParenthesesNum < rightParenthesesNum) {
				oneAns.push_back(')');
				generate(leftParenthesesNum, rightParenthesesNum - 1, oneAns);
				oneAns.pop_back();
			}
			return;
		}
	}
private:
	vector<string> ans;
};