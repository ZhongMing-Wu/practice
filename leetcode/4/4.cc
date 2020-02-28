class Solution {
public:
    double findMedianSortedArrays(vector<int>& nums1, vector<int>& nums2) {
        if(nums1.size()>nums2.size())
            return findMedianSortedArrays(nums2,nums1);
        int MIN_VALUE=0x80000000;
        int MAX_VALUE=0x7fffffff;
        int cut1,cut2;
        int cutl=0;
        int cutr=nums1.size();
        int len=nums1.size()+nums2.size();
        int l1,r1,l2,r2;
        while(1){
            cut1=(cutr-cutl)/2+cutl;
            cut2=len/2-cut1;
            l1=(cut1==0)?MIN_VALUE:nums1[cut1-1];
            r1=(cut1==nums1.size())?MAX_VALUE:nums1[cut1];
            l2=(cut2==0)?MIN_VALUE:nums2[cut2-1];
            r2=(cut2==nums2.size())?MAX_VALUE:nums2[cut2]; 
            if(l1>r2){
                cutr=cut1-1;
            }
            else if(l2>r1){
                cutl=cut1+1;
            }
            else if(len%2==1){
                return min(r1,r2);
            }
            else{
                double leftVal=max(l1,l2);
                double rightVal=min(r1,r2);
                return (leftVal+rightVal)/2;
            }
        }
    }
};