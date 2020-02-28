class Solution {
public:
	void nextPermutation(vector<int>& nums) {
		if (nums.size() == 0)
			return;
		int i = nums.size() - 1;
		int flag = 0;
		while (i>0) {
			if (nums[i - 1] < nums[i]) {
				int k = i;
				while (k < nums.size()) {
					if (nums[i - 1] < nums[k]) {
						k++;
						if (k == nums.size()) {
							swap(nums[i - 1], nums[k - 1]);
							flag = 1;
							break;
						}
					}
					else {
						swap(nums[i - 1], nums[k - 1]);
						flag = 1;
						break;
					}
				}
				break;
			}
			else
				i--;
		}
		int minLoc = i;
		if (!flag) {
			sort(nums.begin(), nums.end());
		}
		else {
			sort(nums.begin() + i, nums.end());
		}
		for (int i = 0; i < nums.size(); i++) {
			if (i == 0)
				cout << "[" << nums[i];
			else
				cout << "," << nums[i];
		}
		cout << "]";
	}
};