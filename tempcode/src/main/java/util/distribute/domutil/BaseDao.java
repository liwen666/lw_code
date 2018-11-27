package util.distribute.domutil;

import java.util.List;

import com.temp.code.sys.abstrac.AbstractEntity;

public interface BaseDao<K,V extends AbstractEntity<String>>{
		public V findDomain(K key);
		public int update(K key);
		public int create(K key);
		public void delete(K key);
		public List<V> findDomainList(K key);
		public EntityPage<V> findDomainPageList(K key);
}