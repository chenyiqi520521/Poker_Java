// * * * * * * * * * * * * * * * * * * * * * * * *
// * REDROCK-TEAM HOMEWORK 3 (20151017)          *
// * Level 4 - Texas Poker                       *
// * Author:  Haruue Icymoon                     *
// * Website: http://www.caoyue.com.cn/          *
// * * * * * * * * * * * * * * * * * * * * * * * *

package cn.com.caoyue.game.poker;

import java.util.Random;  //提供对随机数的支持

public class Poker {
    private Card[] poker;

    //初始化牌堆。按照 spades-2, clubs-2, hearts-2, diamonds-2, ..., spades-10, ..., spades-K, ..., spades-A, ..., BlackJoker, RedJoker 排列
    public Poker(boolean isNeedJoker) {
        int index = 0;
        poker = new Card[isNeedJoker ? 54 : 52];
        for (Card.Point point : Card.Point.values()) {
            for (Card.Suit suit : Card.Suit.values()) {
                poker[index] = new Card(suit.name(), point.name());
                index++;
            }
        }
        if (isNeedJoker) {
            poker[52] = new Card("Black");
            poker[53] = new Card("Red");
        }
    }

    public Card[] getPoker() {
        return poker;
    }

    public Card[] getCards(int startIndex, int numberOfCards) {
        Card[] temp = new Card[numberOfCards];
        System.arraycopy(poker, startIndex, temp, 0, numberOfCards);
        return temp;
    }

    public Card[] getCards(int numberOfCards) {
        return getCards(0, numberOfCards);
    }

    //单次交叉洗牌 (不可单独使用，否则结果无随机性)
    private void crossShuffle() {
        Card[] pokerA = new Card[poker.length / 2];
        System.arraycopy(poker, 0, pokerA, 0, pokerA.length);
        Card[] pokerB = new Card[poker.length / 2];
        System.arraycopy(poker, pokerA.length, pokerB, 0, pokerB.length);
        for (int i = 0; i < pokerA.length + pokerB.length; i++) {
            poker[i] = ((i % 2 == 0) ? pokerA : pokerB)[i / 2];
        }
    }

    //三次交叉洗牌
    public void threeTimesCrossShuffle() {
        crossShuffle();
        crossShuffle();
        crossShuffle();
    }

    //交换两张牌
    private void exchange(int indexOfCard1, int indexOfCard2) {
        Card temp;
        temp = poker[indexOfCard1];
        poker[indexOfCard1] = poker[indexOfCard2];
        poker[indexOfCard2] = temp;
    }

    //交换法洗牌
    public void exchangeShuffle() {
        for (int i = poker.length - 1; i > 0; i--) {
            exchange(i, new Random().nextInt(i));
        }
    }

    //Reference: http://att.newsmth.net/att.php?p.1032.47005.1743.pdf
    //数组下标从1开始，from是圈的头部，mod是要取模的数 mod 应该为 2 * n + 1，时间复杂度O(圈长）
    private void cycleLeader(int from, int mod) {
        Card last = poker[from], t;
        int i;
        for (i = from * 2 % mod; i != from; i = i * 2 % mod) {
            t = poker[i];
            poker[i] = last;
            last = t;
        }
        poker[from] = last;
    }

    //翻转字符串时间复杂度O(to - from)
    private void reverse(int from, int to) {
        Card t;
        for (; from < to; ++from, --to) {
            t = poker[from];
            poker[from] = poker[to];
            poker[to] = t;
        }
    }

    //循环右移num位 时间复杂度O(n)
    private void rightRotate(int num, int n) {
        reverse(1, n - num);
        reverse(n - num + 1, n);
        reverse(1, n);
    }

    //完美洗牌算法 (不可单独使用，否则结果无随机性) 时间O(n)，空间O(1)
    private void inShuffle() {
        int n = poker.length / 2 - 1, k, m;
        for (k = 1; !(Math.pow(3, k) <= 2 * n && 2 * n < Math.pow(3, k + 1)); k++) ;
        m = (int) ((Math.pow(3, k) - 1) / 2);
        rightRotate(m, n);
        for (int i = 0; i < k; i++) {
            cycleLeader((int) Math.pow(3, i), 2 * n + 1);
        }
        reverse(2 * m + 1, 2 * n);
        exchange(poker.length - 1, new Random().nextInt(poker.length));
        exchangeShuffle();
    }

    //奇后置，偶前置 (不可单独使用，否则结果无随机性)
    private void gatherOddEven() {
        Card[] tempPoker = new Card[poker.length];
        for (int i = 0; i < poker.length / 2; i++) {
            tempPoker[i] = poker[2 * i];
        }
        for (int i = 1; i <= poker.length / 2; i++) {
            tempPoker[poker.length / 2 + i - 1] = poker[2 * i - 1];
        }
        poker = tempPoker;
    }

    //这才是第三种洗牌方法——随机方法选择
    public void randomMethodShuffle() {
        for (int i = 1; i <= 5; i++) {
            switch (new Random().nextInt(4)) {
                case 0:
                    crossShuffle();
                    break;
                case 1:
                    exchangeShuffle();
                    break;
                case 2:
                    inShuffle();
                    break;
                case 3:
                    gatherOddEven();
                    break;
            }
        }
    }
}
