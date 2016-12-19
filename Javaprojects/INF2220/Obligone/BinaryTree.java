
/*This class has several methods  like insert, search,delete,height 
firstword ,lastword, nodePerdepth etc..
 It has private classs BinaryNode which implemlemts all of the above method 
recursively
The other method is also the treeStatics which printed upon exing the program.
*/
public class BinaryTree {
	private BinaryNode root;

	public BinaryTree(String data) {
		this.root = new BinaryNode(data, null, null);
	}

     /*inserting the words to the binary  */
	public void insert(String toInsert) {
		root.insert(toInsert);

	}

	/* search a word from the binarytree */

	public String search(String toSearch) {
		String retur = null;
		BinaryNode src = root.search(toSearch);
		if (src == null) {
			retur = null;
		} else {
			retur = src.data;
		}
		return retur;

	}

	/* removes a word from the binarytree */

	public void delete(String toDelete) {
		root.delete(root, toDelete);

	}

	public int height() {
		return root.height(root);
	}

	/*  finds the first or the smallest word lexographically*/

	public String firstWord() {
		return root.firstWord(root).data;
	}

	/*  finds the last or the biggest word lexographically*/
	public String lastWord() {
		return root.lastWord(root).data;
	}

	public int[] nodesPerDepth() {
		int[] depth = new int[height() + 1];
		root.nodesPerDepth(depth, 0);
		return depth;

	}

	/* prints the statics of the binarytree after quiting*/

	public void treeStatics() {
		int h = height(); // height of the binarytree
		int sumOfAllheights = 0;
		double avgHeigt  = 0;
		 int sumOfNodes = 0;
		int[] npd = nodesPerDepth();     
		System.out.println("the height of the tree is   " + h);
                System.out.println("Nodes per height is ");
		for (int i = 0; i < npd.length; i++) {
			System.out.print(i + ":" + npd[i] + ", "); // total node per height 
			sumOfAllheights  += npd[i]*i;    // height multiply by it nodes
			sumOfNodes+=npd[i];        // sum of all nodes
			avgHeigt = sumOfAllheights/sumOfNodes;
			
		}

		System.out.println("");

		System.out.println("average height of all the nodes    " + avgHeigt );
		System.out.println("the first word is   " + firstWord());
		System.out.println("the last word   " + lastWord());
	}
	
    /* the inner binary node class   */
	private class BinaryNode {
		private BinaryNode left, right;  //left and right  root node.
		private String data;            // value of the node

		BinaryNode(String data, BinaryNode left, BinaryNode right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}

		/* searches  words from the binaryNode  recursively*/
		private BinaryNode search(String toSearch) {
			if (toSearch.equals(data)) {
				return this;
			} else if (toSearch.compareTo(data) < 0) {

				return (left == null) ? null : left.search(toSearch);
			} else {
				return (right == null) ? null : right.search(toSearch);
			}
		}

		/* insert words to the binaryNode  recursively*/
		private void insert(String toInsert) {
			if (root == null) {
				root = new BinaryNode(toInsert, null, null);
			} else if (toInsert.compareTo(data) < 0) {
				if (left == null)
					left = new BinaryNode(toInsert, null, null);
				else
					left.insert(toInsert);
			} else {
				if (right == null)
					right = new BinaryNode(toInsert, null, null);
				else
					right.insert(toInsert);
			}
		}

		/* removes a word from the binarytree   recursively*/
		private BinaryNode delete(BinaryNode e, String toDelete) {
			if (e == null) {
				return e;
			}
			if (toDelete.compareTo(e.data) < 0) {
				e.left = delete(e.left, toDelete);
			} else if (toDelete.compareTo(e.data) > 0) {
				e.right = delete(e.right, toDelete);
			} else if (e.left != null && e.right != null) {

				e.data = firstWord(e.right).data;
				e.right = delete(e.right, e.data);
			} else {
				e = (e.left != null) ? e.left : e.right;
			}
			return e;
		}

		/* it give the maximum height of the tree  recursively */
		private int height(BinaryNode e) {
			if (e == null)
				return -1;
			else
				return 1 + Math.max(height(e.left), height(e.right));
		}

		/*it give the the smallest word lexicographically on any side its called from*/
		private BinaryNode firstWord(BinaryNode e) {
			if (e != null) {
				while (e.left != null) {
					e = e.left;
				}
			}
			return e;
		}

		/*it give the the largest word lexicographically on any side its called from*/
		private BinaryNode lastWord(BinaryNode e) {
			if (e != null) {
				while (e.right != null) {
					e = e.right;
				}
			}
			return e;
		}

		/* it give the number of word per depth recursively*/

		void nodesPerDepth(int[] depth, int nodes) {
			if (root == null)
				return;

			if (left != null)
				left.nodesPerDepth(depth, nodes + 1);
			if (right != null)
				right.nodesPerDepth(depth, nodes + 1);

			depth[nodes]++;
		}
	}

}
