class Solution {
public:
	ListNode* reverseKGroup(ListNode* head, int k) {
		ListNode *newHead = new ListNode(1);
		ListNode *tail = new ListNode(1);
		newHead->next = tail;
		ListNode *startPointer = head;
		while (judgeLength(startPointer, k)) {
			startPointer = reversePart(startPointer, k, tail);
		}
		if (startPointer != NULL)
			tail->next = startPointer;
		return newHead->next->next;
	}
	bool judgeLength(ListNode* start, int k) {  //判断剩余节点是否有k个
		while (k > 0) {
			if (start != NULL) {
				start = start->next;
				k--;
			}
			else
				return false;
		}
		return true;
	}

	ListNode* reversePart(ListNode* start, int k, ListNode* &tail) {
		ListNode *first = NULL, *second = NULL, *third = NULL;  //nexstStart 是下一部分链表的头指针
		first = start;
		second = first->next;
		first->next = NULL;
		for (int i = 1; i < k; i++) {
			third = second->next;
			second->next = first;
			first = second; 
			second = third;
		}
		tail->next = first;
		tail = start;
		return third;
	}
};