package com.fang.datastructure.linkedlist;

public class MyLinkedList {

    private Node head ;

    /**
     * 增加操作
     *     直接在链表的最后插入新增的节点即可
     *     将原本最后一个节点的next指向新节点
     */
    public void addNode(Node node){
        //链表中有节点，遍历到最后一个节点
        Node temp = head;   //一个移动的指针(把头节点看做是一个指向节点的指针)
        while(temp.next != null){   //遍历单项链表,直到遍历到最后一个则跳出循环
            temp = temp.next;       //往后移一个节点，指向下一个节点
        }
        temp.next = node;           //temp为最后一个节点或者是头结点，将其next指向新节点
    }

    /**
     * 插入节点到指定位置
     * insertNodeByIndex:在链表的指定位置插入节点，插入操作需要知道一个节点即可：当前位置的前一个节点
     * index：插入链表的位置，从1开始
     * node：插入的节点
     */
    public void insertNodeByIndex(int index, Node node){
        //首先判断指定位置是否合法
        if (index < 1 || index > length() + 1){
            System.out.println("插入位置不合法。");
            return;
        }
        int length = 1;     //记录我们遍历到第几个节点，也就是记录位置
        Node temp = head;   //可移动的指针
        while(head.next != null){   //遍历单项链表
            if(index == length++){  //判断是否到达指定位置
                //注意，我们的temp代表的是当前位置的前一个节点
                //前一个节点     当前节点        后一个节点
                //temp           temp.next      temp.next.next
                //插入操作
                node.next = temp.next;
                temp.next = node;
                return;
            }
            temp = temp.next;
        }
    }

    /**
     * 删除指定位置上的节点
     * 通过index删除指定位置的节点，跟指定位置增加节点是一样的，先找到准确位置。然后进行删除操作
     * 删除操作要知道一个节点即可：当前位置的前一个节点
     * @param index: 链表中的位置，从1开始
     */
    public void delNodeByIndex(int index){
        //判断index是否合理
        if (index < 1 || index > length()){
            System.out.println("给定的位置不合理");
            return;
        }
        //步骤跟insertNodeByIndex是一样的，只是操作不一样
        int length = 1;
        Node temp = head;
        while(temp.next != null){
            if (index == length++){
                //删除操作
                temp.next = temp.next.next;
                return;
            }
            temp = temp.next;
        }
    }

    /**
     * 单链表进行选择排序
     * 对链表中的节点进行排序，按照从小到大的顺序，使用选择排序
     * 使用双层遍历，第一层遍历，正常遍历链表，第二层遍历，遍历第一层遍历时所用的节点后面所有节点并与之比较
     *
     */
    public void selectSortNode(){
        //判断链表长度大于2，不然只有一个元素就不用排序了
        if (length() < 2){
            System.out.println("不用排序");
            return;
        }
        //选择排序
        Node temp = head;      //第一层遍历使用的移动指针
        while (temp.next != null){
            Node secondTemp = temp.next;
            while (secondTemp.next != null){
                if (temp.next.data > secondTemp.next.data){
                    int t = secondTemp.next.data;
                    secondTemp.next.data = temp.next.data;
                    temp.next.data = t;
                }
                secondTemp = secondTemp.next;
            }
            temp = temp.next;
        }
    }

    /**
     * 单链表进行插入排序
     * 对链表进行插入排序，按从大到小的顺序，只要这里会写，那么手写数组插入排序
     * 也是一样的。先要明白原理。什么是插入排序，这样才好写代码
     * 插入排序，分两组，一组是有序序列，将无序组中的元素与有序组中的元素进行比较(如何比较，那么就要知道插入排序的原理是什么)
     * 这里我想到的方法是，构建一个空的链表当成有序序列，而原先的旧链表为无序序列，按照原理，一步步进行编码即可
     */
    public void insertSortNode(){
        //判断链表长度大于2，不然只有一个元素，就不用排序了
        if (length() < 2){
            System.out.println("没必要");
            return;
        }
        //创建新链表
        Node newHead = new Node(0); //新链表的头指针
        Node newTemp = newHead; //新链表的移动指针
        Node temp = head; //旧链表的移动指针
        if (newTemp.next == null){  //将第一个节点直接放入新链表中
            Node node = new Node(temp.next.data);
            newTemp.next = node;
            temp = temp.next;   //旧链表中指针一道下一位(第二个节点处)
        }
        while(temp.next != null){   //遍历现有链表
            while(newTemp.next != null){
                //先跟新链表中的第一个节点进行比较，如果符合条件则添加到新链表，注意是在第一个位置上增加节点
                //如果不符合，则跟新链表中第二个节点进行比较，如果都不符合，跳出while判断是否到了新链表的最后一个节点，如果是则直接在新链表后面添加即可
                if (newTemp.next.data < temp.next.data){
                    Node node = new Node(temp.next.data);
                    node.next = newTemp.next;
                    newTemp.next = node;
                    break;
                }
                newTemp = newTemp.next;
            }
            if (newTemp.next == null){//到达最后末尾还没符合，那么说明该值是新链表中最小的数，直接添加到链表中即可
                //直接在新链表后面添加
                Node node = new Node(temp.next.data);
                newTemp.next = node;
            }
            //旧链表指针指向下一位节点，继续重复和新链表中的节点进行比较
            temp = temp.next;
            //新链表中的移动指针需要复位，指向头节点
            newTemp = newHead;
        }
        //开始使用新链表，旧链表等待垃圾回收机制将其收回
        head = newHead;

    }



    /**
     * 计算单链表的长度
     */
    public int length(){
        int length = 0;
        Node temp = head;
        while(temp.next != null){
            length++;
            temp = temp.next;
        }
        return length;
    }

    /**
     * 遍历单链表
     * 打印data
     */
    public void print(){
        Node temp = head.next;
        while(temp != null){
            System.out.println(temp.data + ",");
            temp = temp.next;
        }
        System.out.println();
    }
}
