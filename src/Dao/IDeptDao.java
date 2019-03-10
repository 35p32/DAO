package Dao;

import vo.Dept;
/**
 *这里轻松地使用了 IDAO标准接口
 *IDAO接口 有泛型，这使得我们很方便地去重用
 * @author 35.32
 *
 */
public interface  IDeptDao  extends IDAO<Integer ,Dept>{
	 
}
