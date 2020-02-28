class Solution {
    public int[][] reconstructQueue(int[][] people) {
        if(people.length == 0) {
            return people;
        }
        mergeSort(people, 0, people.length - 1);
        //Arrays.sort(people, (o1, o2) ->  o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0]);
        int k, temp1, temp2, time;
        for(int i = 0; i < people.length; i++) {
            if(people[i][1] < i) {
                k = i - 1;
                time = i - people[i][1];
                temp1 = people[i][0];
                temp2 = people[i][1];
                for(int j = 0; j < time; j++, k--) {
                    people[k + 1][0] = people[k][0];
                    people[k + 1][1] = people[k][1];
                }
                k++;
                people[k][0] = temp1;
                people[k][1] = temp2;
            }
        }
        return people;
    }

    void mergeSort(int[][] people, int left, int right) {
        if(left == right) {
            return;
        }
        int middle = (left + right) / 2;
        mergeSort(people, left, middle);
        mergeSort(people, middle + 1, right);
        int[][] tempArray = new int[right - left + 1][2];
        int i = left, j = middle + 1, k = 0;
        while(i <= middle && j <= right) {
            if(people[i][0] > people[j][0]) {
                tempArray[k][0] = people[i][0];
                tempArray[k][1] = people[i][1];
                i++;
            }
            else if(people[i][0] < people[j][0]) {
                tempArray[k][0] = people[j][0];
                tempArray[k][1] = people[j][1];
                j++;
            }
            else {
                if(people[i][1] < people[j][1]) {
                    tempArray[k][0] = people[i][0];
                    tempArray[k][1] = people[i][1];
                    i++;
                }
                else {
                    tempArray[k][0] = people[j][0];
                    tempArray[k][1] = people[j][1];
                    j++;
                }
            }
            k++;
        }
        while(i <= middle) {
            tempArray[k][0] = people[i][0];
            tempArray[k][1] = people[i][1];
            i++;
            k++;
        }
        while(j <= right) {
            tempArray[k][0] = people[j][0];
            tempArray[k][1] = people[j][1];
            j++;
            k++;
        }
        for(i = left, j = 0; i <= right; i++, j++) {
            people[i][0] = tempArray[j][0];
            people[i][1] = tempArray[j][1];
        }
    }
}