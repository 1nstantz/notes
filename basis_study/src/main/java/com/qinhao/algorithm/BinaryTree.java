package com.qinhao.algorithm;


import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树遍历
 * @author qinhao
 * @date 2022/4/19 - 15:15
 */
public class BinaryTree {
    public List<Integer> list(TreeNode root){
        List<Integer> res=new ArrayList<>();
        helper(res,root);
        return res;
    }

    /**
     * 前序遍历
     * @param res
     * @param root
     */
    private void helper(List<Integer> res, TreeNode root) {
        if (root == null) {
            return ;
        }
        res.add(root.val);
        helper(res,root.left);
        helper(res,root.right);

        //中序
        //helper(res,root.left);
        //res.add(root.val);
        //helper(res,root.right);

        //后序
        //helper(res,root.left);
        //helper(res,root.right);
        //res.add(root.val);
    }
}
