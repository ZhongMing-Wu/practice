class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        map<int,int> toLoc;
        for(int i=0;i<nums.size();i++)
            toLoc[nums[i]]=i+1;
        vector<int> returnVal;
        for(int i=0;i<nums.size();i++){
            int secondVal=target-nums[i];
            if(toLoc[secondVal]!=0&&toLoc[secondVal]!=(i+1)){
                returnVal.push_back(i);
                returnVal.push_back(toLoc[secondVal]-1);
                break;
            }
        }
        return returnVal;
    }
};