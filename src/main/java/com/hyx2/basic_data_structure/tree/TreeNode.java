package com.hyx2.basic_data_structure.tree;

public class TreeNode {

	public int val;
	public TreeNode left;
	public TreeNode right;

	public TreeNode(int val) {
		this.val = val;
	}

	public TreeNode(TreeNode left, int val, TreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}

	@Override
	public String toString() {
		return String.valueOf(this.val);
	}
}
