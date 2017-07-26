package ytu.icoding.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



/**
 * ��һ��list����,�����bean���� parentId תΪ����ʽ
 *
 */
public class TreeUtil {
	
	
	/**
	 * ���ݸ��ڵ��ID��ȡ�����ӽڵ�
	 * @param list �����
	 * @param typeId ����ĸ��ڵ�ID
	 * @return String
	 */
	public static List<TreeObject> getChildResourcess(List<TreeObject> list,int praentId) {
		List<TreeObject> returnList = new ArrayList<TreeObject>();
		for (Iterator<TreeObject> iterator = list.iterator(); iterator.hasNext();) {
			TreeObject t = (TreeObject) iterator.next();
			// һ�����ݴ����ĳ�����ڵ�ID,�����ø��ڵ�������ӽڵ�
			if (t.getParentId()==praentId) {
				recursionFn(list, t);
				returnList.add(t);
			}
		}
		return returnList;
	}
	
	/**
	 * �ݹ��б�
	 * @author LJN
	 * Email: mmm333zzz520@163.com
	 * @date 2013-12-4 ����7:27:30
	 * @param list
	 * @param Resources
	 */
	private static void  recursionFn(List<TreeObject> list, TreeObject t) {
		List<TreeObject> childList = getChildList(list, t);// �õ��ӽڵ��б�
		t.setChildren(childList);
		for (TreeObject tChild : childList) {
			if (hasChild(list, tChild)) {// �ж��Ƿ����ӽڵ�
				//returnList.add(Resources);
				Iterator<TreeObject> it = childList.iterator();
				while (it.hasNext()) {
					TreeObject n = (TreeObject) it.next();
					recursionFn(list, n);
				}
			}
		}
	}
	
	// �õ��ӽڵ��б�
	private static List<TreeObject> getChildList(List<TreeObject> list, TreeObject t) {
		
		List<TreeObject> tlist = new ArrayList<TreeObject>();
		Iterator<TreeObject> it = list.iterator();
		while (it.hasNext()) {
			TreeObject n = (TreeObject) it.next();
			if (n.getParentId() == t.getId()) {
				tlist.add(n);
			}
		}
		return tlist;
	}

	// �ж��Ƿ����ӽڵ�
	private static boolean hasChild(List<TreeObject> list, TreeObject t) {
		return getChildList(list, t).size() > 0 ? true : false;
	}
	
	// ����ģ�����ݲ���
	public static void main(String[] args) {
		/*long start = System.currentTimeMillis();
		List<Resources> ResourcesList = new ArrayList<Resources>();
		
		ResourcesUtil mt = new ResourcesUtil();
		List<Resources> ns=mt.getChildResourcess(ResourcesList,0);
		for (Resources m : ns) {
			System.out.println(m.getName());
			System.out.println(m.getChildren());
		}
		long end = System.currentTimeMillis();
		System.out.println("��ʱ:" + (end - start) + "ms");*/
	}
	
}
