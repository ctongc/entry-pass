package interview;

import java.util.*;

class ListNode {
    int value;
    ListNode next;

    public ListNode(int value) {
        this.value = value;
    }

    public ListNode(int value, ListNode next) {
        this.value = value;
        this.next = next;
    }
}

class TreeNode {
    public int key;
    public TreeNode left;
    public TreeNode right;
    // public TreeNode parent;

    public TreeNode(int key) {
        this.key = key;
    }
}

public class MicrosoftCn {

    /**
     * Return the maximum possible value obtained by deleting one '5' digit from the decimal representation
     * It's guaranteed that num has at least one '5'
     *
     * Time = O(n)
     * Space = O(n)
     */
    public int removeFive(int num) {
        int sign = num >= 0 ? 1 : -1;
        String numString = Integer.toString(num); // this will keep the sign
        boolean foundFivePos = false;
        StringBuilder sb = new StringBuilder();
        if (sign == -1) {
            for (int i = 0; i < numString.length(); i++) {
                if (numString.charAt(i) == '5' && !foundFivePos) {
                    foundFivePos = true;
                    continue;
                }
                sb.append(numString.charAt(i));
            }
        } else {
            for (int i = 0; i < numString.length(); i++) {
                if (numString.charAt(i) == '5' && !foundFivePos) {
                    foundFivePos = true;
                    continue;
                }
                sb.append(numString.charAt(i));
            }
        }
        return Integer.parseInt(sb.toString());
    }

    /**
     * Min Deletions To Obtain String in Right Format
     * Given a string with only characters X and Y. Find the minimum number of characters
     * to remove from the string such that there is no interleaving of character X and Y
     * and all the Xs appear before any Y.
     *
     * e.g. Input: BAAABAB
     * Output: 2
     * We can obtain AAABB by:
     * Delete first B -> AAABAB
     * Delete last occurrence of A -> AAABB
     *
     * e.g. Input:BBABAA
     * Output: 3
     * We can remove all occurrence of A or B
     */
    public int minStep(String str) {
        int numB = 0;
        int minDel = 0;

        //      BAAABAB
        //            i
        // numB 1111223
        // minD 0111122
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'A') {
                minDel = Math.min(numB, minDel + 1);
            } else {
                numB++;
            }
        }
        return minDel;
    }

    /**
     * Given an array A of N integers, returns the smallest positive integer (greater than 0) that does not occur in A.
     *
     * e.g. A = [1, 3, 6, 4, 1, 2], return 5
     * e.g. A = [1, 2, 3], return 4
     * e.g. A = [−1, −3], return 1.
     *
     * assumptions:
     * 1. N is an integer within the range [1..100,000];
     * 2. each element of array A is an integer within the range [−1,000,000..1,000,000].
     */
    public int solution(int[] A) {
        if (A == null || A.length == 0) {
            return 1;
        }

        boolean negCheck = false;
        boolean[] lookup = new boolean[A.length + 1];
        for (int i : A) {
            if (i > 0) {
                negCheck = true;
            }
            if (i > 0 && i <= A.length) {
                lookup[i] = true;
            }
        }

        if (!negCheck) {
            return 1;
        }

        for (int i = 1; i < lookup.length; i++) {
            if (!lookup[i]) {
                return i;
            }
        }

        return A.length + 1;
    }

    /**
     * Bing团队: 先增后减的数组，求最大的index
     */
    public int maxInPeekLikeArray(int[] array) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (array[mid] < array[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    /**
     * Azure: 查找数组中第一个大于target的数字返回下标
     */
    public int smallestElementLargerThanTarget(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }
        // {1, 2, 2, 2, 3}, T = 2
        //  l     M     r
        //  case 1: a[mid] = T -> left = mid
        //  case 2: a[mid] < T -> left = mid
        //  case 3: a[mid] > T -> right = mid
        int left = 0;
        int right = array.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (array[mid] <= target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        // post-processing
        if (array[left] > target) {
            return left;
        }
        if (array[right] > target) {
            return right;
        }
        return - 1;
    }

    /**
     * Ads: 657 模拟机器人上下左右走动最后判断是否能返回原点
     */
    public boolean judgeCircle(String moves) {
        int ver = 0;
        int hor = 0;
        for (char move : moves.toCharArray()) {
            if (move == 'U') {
                ver++;
            }
            if (move == 'D') {
                ver--;
            }
            if (move == 'R') {
                hor++;
            }
            if (move == 'L') {
                hor--;
            }
        }
        return ver == 0 && hor == 0;
    }
    /**
     * Ads: meeting schedule
     */
    public int maxMeetings(int[][] intervals) {
        // perform greedy
        // 1. sort by ending time
        // 2. pick by ending time without conflicts
        Arrays.sort(intervals, Comparator.comparingInt(m -> m[1]));

        int count = 1;
        int lastMeetingEndTime = intervals[0][1];
        for (int[] meetings : intervals) {
            if (meetings[0] > lastMeetingEndTime) {
                count++;
                lastMeetingEndTime = meetings[1];
            }
        }
        return count;
    }

    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(m -> m[0]));

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int meetingRoom = 1;
        minHeap.offer(intervals[0][1]);
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < minHeap.peek()) {
                minHeap.offer(intervals[i][1]);
                meetingRoom = Math.max(meetingRoom, minHeap.size());
            } else {
                minHeap.poll();
                minHeap.offer(intervals[i][1]);
            }
        }
        return meetingRoom;
    }
    /**
     * LeetCode 739 每日温度
     */
    public int[] dailyTemperatures(int[] temperatures) {
        // updating warmerDay[i] while i has been popped out from the stack
        // 1. if curTemp > stack.peek(), which is j,
        //    stack.pop(), update warmerDay[j], until curTemp < stack.peek(), stack.push(curTemp)
        // 2. if no date to update or curTemp < stack.peek(), no day can be updated, stack.push(curTemp)
        int[] warmerDay = new int[temperatures.length];
        Deque<Integer> stack = new ArrayDeque<>(); // warmerDays that hasn't been updated
        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int date = stack.pollFirst();
                warmerDay[date] = i - date;
            }
            stack.offerFirst(i);
        }
        return warmerDay;
    }

    /**
     * LeetCode 261 判断图是否是二叉树
     * Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is a pair of nodes),
     * write a function to check whether these edges make up a valid tree
     */
    public boolean validTree(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            // 双向连接
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        Set<Integer> visited = new HashSet<>();
        // A tree is a special undirected graph. It satisfies two properties
        // - It is connected
        // - It has no cycle
        return dfs(graph, 0, -1, visited) && visited.size() == n;
    }

    private boolean dfs(List<List<Integer>> graph, int node, int pre, Set<Integer> visited) {
        if (!visited.add(node)) {
            return false;
        }
        for (int neighbor : graph.get(node)) {
            if (neighbor != pre) { // 查重时排除父节点
                if(!dfs(graph, neighbor, node, visited)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 技术面1：反转链表、每隔k个反转链表
     */
    public ListNode reverseLinkedList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode prev = null;
        while (head != null) { // if using while (head.next != null) then it won't reverse the last element
            ListNode nextNode = head.next; // always store the new head first
            head.next = prev; // reserve happens here, 记住是在reverse head->prev而不是head.next->head
            prev = head; // move prev by one
            head = nextNode; // move current by one
        }
        // now head == null
        return prev;
    }

    public ListNode reverseLinkedListRecursive(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseLinkedListRecursive(head.next); // newHead is the last node of current linked list

        head.next.next = head; // head.next is the next node
        head.next = null; // reset

        return newHead;
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        // post-processing
        ListNode prev = null;
        ListNode cur = head;
        for (int i = 0; i < left - 1; i++) {
            prev = cur;
            cur = cur.next;
        }
        ListNode frontTail = prev;
        ListNode newLinkTail = cur;

        // start reversing
        for (int i = 0; i < right - left + 1; i++) {
            ListNode nextNode = cur.next;
            cur.next = prev;
            prev = cur;
            cur = nextNode;
        }

        // post-processing
        if (frontTail != null) {
            frontTail.next = prev;
        } else { // frontTail == null
            head = prev; // head == newLinkTail, prev points to the newLinkHead
        }
        newLinkTail.next = cur;
        return head;
    }

    public ListNode reverseInPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummyHead = new ListNode(0);
        ListNode prev = dummyHead;
        while (head != null && head.next != null) {
            prev.next = head.next;
            head.next = head.next.next;
            prev.next.next = head;
            prev = head;
            head = head.next;
        }
        return dummyHead.next;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k == 1) {
            return head;
        }

        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode lastKTail = dummyHead;
        ListNode cur = head;

        int index = 0;
        while (cur != null) {
            cur = cur.next;
            index++;
            if (index % k == 0) {
                lastKTail = reverseBetweenTwoNodes(lastKTail, cur); // points to the lastNode in last group
                cur = lastKTail.next;
            }
        }
        return dummyHead.next;
    }

    private ListNode reverseBetweenTwoNodes(ListNode start, ListNode end) {
        // t(start)            c(end)
        // A -> 1 -> 2 -> 3 -> B
        //      l    c
        // A    1 <- 2    3 -> B
        // |_________|    n
        //                c
        // A    1 <- 2 <- 3 -> B
        // |______________|    n
        //                     c
        // A -> 3 -> 2 -> 1 -> B

        ListNode newLastNode = start.next;
        ListNode cur = newLastNode.next; // reverse from the second node in link

        while(cur != end) {
            ListNode next = cur.next;
            cur.next = start.next;
            start.next = cur;
            cur = next;
        }
        newLastNode.next = end;

        return newLastNode;
    }

     /**
      * 技术面4：组内leader，n个球内选k个，所有的排列组合
      */
     public int nChooseK(int n, int k) {
         if (n < k) {
             return 0;
         }
         if (n == k) {
             return 1;
         }
         if (n == 0) {
             return 0;
         }
         return nChooseK(n - 1, k - 1) + nChooseK(n - 1, k);
     }

    public List<List<String>> nChooseK(String[] n, int k) {
         if (n == null || n.length == 0 || n.length < k) {
             return Collections.emptyList();
         }
         List<List<String>> result = new ArrayList<>();
         nChooseKAllPerm(n, k, 0, new ArrayList<>(), result);
         return result;
    }

    private void nChooseKAllPerm(String[] n, int k, int index, List<String> cur, List<List<String>> result) {
         if (cur.size() == k) {
             result.add(new ArrayList<>(cur));
             return;
         }

         if (cur.size() + n.length - index < k) {
             return;
         }

         cur.add(n[index]);
         nChooseKAllPerm(n, k, index + 1, cur, result);
         cur.remove(cur.size() - 1);

         nChooseKAllPerm(n, k, index + 1, cur, result);
    }

    /**
     * 大老板面：树的反序列化、二叉树查找父节点
     */
    public String serialize(TreeNode root) {
        if (root == null) {
            return "null,";
        }
        return root.key + "," + serialize(root.left) + serialize(root.right);
    }

    public TreeNode deserialize(String data) {
        String[] dataArray = data.split(",");
        List<String> dataList = new ArrayList<>(Arrays.asList(dataArray));
        return deserializer(dataList);
    }

    private TreeNode deserializer(List<String> dataList) {
        if ("null".equals(dataList.get(0))) {
            dataList.remove(0);
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(dataList.get(0)));
        dataList.remove(0);
        root.left = deserializer(dataList);
        root.right = deserializer(dataList);
        return root;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode leftLCA = lowestCommonAncestor(root.left, p, q);
        TreeNode rightLCA = lowestCommonAncestor(root.right, p, q);

        if (leftLCA != null && rightLCA != null) {
            return root;
        }

        return leftLCA == null ? rightLCA : leftLCA;
    }

    public TreeNode lcaNotGuaranteeInTree(TreeNode root, TreeNode one, TreeNode two) {
        if (root == null) {
            return null;
        }

        TreeNode result = lowestCommonAncestor(root, one, two);

        if (result != one && result != two && result != null) {
            return result;
        }

        if (result == one) {
            if (two == null || lowestCommonAncestor(one, two, two) != null) {
                return result;
            }
        } else if (result == two) {
            if (one == null || lowestCommonAncestor(two, one, one) != null) {
                return result;
            }
        }
        return null;
    }

    /**
     * 下一个排列，给一个数字，找出下一个排列
     */
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        int left = nums.length - 2;
        while (left >= 0 && nums[left] >= nums[left + 1]) {
            left--;
        }

        // if left < 0, means nums is in descending order: [9, 5, 4, 3, 1]
        // then no next larger permutation is possible and reverse the whole
        if (left >= 0) {
            int right = nums.length - 1;
            while(nums[right] <= nums[left]) {
                right--;
            }
            swap(nums, left, right);
        }

        reverse(nums, left + 1, nums.length - 1);
    }

    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i++, j--);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 技术面2：String to Double实现
     * 技术面3：系统设计题：京东优惠券，大致思路，关键数据结构设计，数据存储等等
     * 2轮 做一个Throttling downloader. 类似于实现一个可以限制速度的下载器。用的token bucket算法完成的。
     * 3轮 做一个支持20000000user的排名系统，User登录评论可以加分，要返回user的排名
     * 4轮 问了一些java的内容，GC, 多线程, 然后系统设计tiny url
     */

    public static void main(String[] args) {
        MicrosoftCn ins = new MicrosoftCn();
        System.out.println(ins.removeFive(15958));
        System.out.println(ins.removeFive(-9995));
        System.out.println(ins.removeFive(-5859));
        System.out.println(ins.removeFive(-5000));

        System.out.println(ins.minStep("AAABBB"));
        System.out.println(ins.minStep("BBAABBBB"));
        System.out.println(ins.minStep("BAAABAB"));

        ins.solution(new int[]{1,3,6,4,1,2});
        System.out.println(ins.validTree(3, new int[][]{{1,0},{0,2},{2,1}}));

        List<List<String>> nChooseK = ins.nChooseK(new String[]{"a","b","c","d","e","f","g"}, 3);
        System.out.println(nChooseK.size());
        System.out.println(ins.nChooseK(7, 3));
    }
}
