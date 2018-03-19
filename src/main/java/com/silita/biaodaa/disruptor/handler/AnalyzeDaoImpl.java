//package com.silita.biaodaa.disruptor.handler;
//
//import cloud.simple.service.commons.jdbc.JdbcBase;
//import cloud.simple.service.commons.utils.CommonUtil;
//import com.snatch.model.AreaType;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Map;
//
//
//@Repository
//public class AnalyzeDaoImpl extends JdbcBase implements AnalyzeDao{
//
//	/**
//	 * 规则范围查询
//	 */
//	@Override
//	@Cacheable(value = "analyzeRangeCache", key="#field")
//	public List<Map<String, Object>> queryAnalyzeRangeByField(String field){
//		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList("select * from mishu_snatch.analyze_range where field=?", field);
//		return list;
//	}
//
//	/**
//	 * 报名地址库
//	 */
//	@Override
//	@Cacheable(value = "analyzeRangeBmAddrCache", key="'analyzeRangeBmAddr'")
//	public List<String> queryAnalyzeRangeBmAddr(){
//		List<String> addrList = this.getJdbcTemplate().queryForList("select address from mishu_snatch.analyze_range_bmaddr",String.class);
//		return addrList;
//	}
//
//	/**
//	 * 开标地址库
//	 */
//	@Override
//	@Cacheable(value = "analyzeRangeKbAddrCache", key="'analyzeRangeKbAddr'")
//	public List<String> queryAnalyzeRangeKbAddr(){
//		List<String> addrList = this.getJdbcTemplate().queryForList("select address from mishu_snatch.analyze_range_kbaddr ORDER BY id ASC",String.class);
//		return addrList;
//	}
//
//
//	/**
//	 * 资质词典表
//	 */
//	@Override
//	@Cacheable(value = "allZhCache", key="'findsAllCategory'")
//	public List<Map<String, Object>> queryzh() {
//		List<Map<String,Object>> list = null;
//		String sql ="select `name`,mainUUid,rank from mishu.all_zh where finalUuid <>'' and finalUuid is not null";
//		list=this.getJdbcTemplate().queryForList(sql,new Object[]{});
//		return list;
//	}
//
//	/**
//	 * 资质要求插入表
//	 */
//	@Override
//	public void insertSnatchUrlCertAnalyze(int id, List<Map<String, Object>> zh) {
//		for (int k = 0; k < zh.size(); k++) {
//			String sqlname="SELECT MIN(id) id, majorName from mishu.aptitude_dictionary where majorUUid = ?";
//			Map<String, Object> mapname = this.getJdbcTemplate().queryForMap(sqlname, zh.get(k).get("uuid"));	//查询标准名称
//			if(mapname !=null){
//				if(mapname.get("majorName")!=null && zh.get(k).get("rank") !=null){
//					String certificate=mapname.get("majorName").toString()+ CommonUtil.spellRank(zh.get(k).get("rank").toString());	//规范化资质名称
//					String uuid = CommonUtil.spellUuid(zh.get(k).get("uuid").toString(), zh.get(k).get("rank").toString());	//拼接资质uuid
//					this.getJdbcTemplate().update("INSERT INTO mishulog.snatch_url_cert_analyze (contId,certificate,certificateUUid,type,licence)VALUES(?,?,?,?,?)", id,certificate,uuid.replaceAll("'", ""),zh.get(k).get("type"),zh.get(k).get("licence"));
//				}
//			}
//		}
//	}
//
//	/**
//	 * 查询项目类型
//	 */
//	@Override
//	public String queryAptitudeProjtype(String mainUuid) {
//		return this.getJdbcTemplate().queryForObject("select MIN(name) from mishu.aptitude_projtype where mainUuid=?", new Object[]{mainUuid}, String.class);
//	}
//
//	/**
//	 * 查询项目地区
//	 */
//	@Override
//	@Cacheable(value = "areaCache", key="#grade")
//	public List<Map<String, Object>> queryAreaByGrade(int grade) {
//		List<Map<String,Object>> list = null;
//		String sql ="select id,`name`,parent_id from mishu.area where grade=?";
//		list=this.getJdbcTemplate().queryForList(sql, grade);
//		return list;
//	}
//
//	/**
//	 * 评标办法库
//	 */
//	@Override
//	@Cacheable(value = "analyzeRangePbModeCache", key="'analyzeRangePbMode'")
//	public List<Map<String, Object>> queryAnalyzeRangePbMode() {
//		return this.getJdbcTemplate().queryForList("select anotherName, standardName from mishu_snatch.analyze_range_pbmode ORDER BY id");
//	}
//
//	/**
//	 * 查询湖南下的全部数据
//	 * @param
//	 * @return
//	 */
//	@Override
//	@Cacheable(value = "analyzeHuNanAreaCache", key="'analyzeHuNanArea'")
//	public List<AreaType> queryHuNanArea(int grade) {
//		List<AreaType> list = null;
//		//SELECT * FROM mishu.area WHERE grade=?;
//		//SELECT * FROM mishu.area WHERE path like '%402881882ba8753a012ba930d6690115%'and grade=?
//		String sql ="SELECT * FROM mishu.area WHERE grade=? and display_name not like '山东%'";
//		list=this.querys(sql, new Object[]{grade}, AreaType.class);
//		//list=this.getJdbcTemplate().queryForList(sql, new Object[]{grade}, AreaType.class);
//		return list;
//	}
//
//	/**
//	 * 根据id得到项目标题
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public String queryProjType(int id) {
//		String sql = "SELECT title FROM mishu.snatchurl WHERE mishu.snatchurl.id=?";
//		String title = this.getJdbcTemplate().queryForObject(sql, new Object[]{id}, String.class);
//		return title;
//	}
//
//	/**
//	 * 查询项目类型
//	 * @return
//	 */
//	@Override
//	@Cacheable(value = "analyzeProjTypeCache", key="'analyzeProjType'")
//	public List<String> queryProjType() {
//		String sql = "SELECT mishu.aptitude_projtype.`name` FROM mishu.aptitude_projtype ";
//		return this.getJdbcTemplate().queryForList(sql, String.class);
//	}
//
//	/**
//	 * 查询资金来源
//	 * @return
//	 */
//	@Override
//	@Cacheable(value = "analyzeSourceCache", key = "'analyzeMoneySource'")
//	public List<String> queryAnalyzeMoneySource() {
//		List<String> moneySourceList = this.getJdbcTemplate().queryForList("SELECT moneySource FROM mishu.money_source  ORDER BY LENGTH(moneySource) DESC",String.class);
//		return moneySourceList;
//	}
//
//	/**
//	 * 查詢湖南全部县市
//	 * @return
//	 */
//	@Override
//	public List<AreaType> querysCity() {
//		String sql = "SELECT * FROM mishu.area WHERE grade='2' and path like '%402881882ba8753a012ba930d6690115%' ORDER BY LENGTH(mishu.area.name) DESC";
//		return this.querys(sql, new Object[]{}, AreaType.class);
//		//return this.getJdbcTemplate().queryForList(sql, AreaType.class);
//	}
//
//	@Override
//	@Cacheable(value = "huNanAreaByIdCache", key="#parentid")
//	public String getHuNanAreaByParentId(String parentid) {
//		String sql = "SELECT name FROM mishu.area where id=?";
//		String area = this.getJdbcTemplate().queryForObject(sql, new Object[]{parentid}, String.class);
//		return area;
//	}
//
//	/**
//	 * 查询标准化报名地址
//	 * @param address 地址
//	 * @return 标准化报名地址
//	 */
//	@Override
//	public List<String> queryBaseBmAddress(String address){
//		String sql = "SELECT address FROM mishu_snatch.analyze_range_bmaddr " +
//				"WHERE id=(SELECT baseAddrId FROM mishu_snatch.analyze_range_bmaddr_alias " +
//				"WHERE alias=?)";
//		try{
//			return this.getJdbcTemplate().queryForList(sql,new Object[]{address},String.class);
//		}catch (EmptyResultDataAccessException e){
//			logger.error("该地址没有标准化地址");
//			return null;
//		}
//	}
//
//	/**
//	 * 查询标准化开标地址
//	 * @param address 地址
//	 * @return 标准化开标地址
//	 */
//	@Override
//	public List<String> queryBaseKbAddress(String address){
//		String sql = "SELECT address FROM mishu_snatch.analyze_range_kbaddr " +
//				"WHERE id=(SELECT baseAddrId FROM mishu_snatch.analyze_range_kbaddr_alias WHERE alias=?)";
//		try{
//			return this.getJdbcTemplate().queryForList(sql,new Object[]{address},String.class);
//		}catch (EmptyResultDataAccessException e){
//			logger.error("该地址没有标准化地址");
//			return null;
//		}
//	}
//
//
//}
