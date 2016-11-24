package com.zc.simple.test;

import java.io.IOException;
import java.util.Map;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

public class ESCilent {

	private Client client;
	//create client
	public void initESClient() {
		/*// �������es,��������ֻ�����˼�Ⱥ����,Ĭ����elasticsearch,�����������ͬ
		Settings settings = ImmutableSettings.settingsBuilder()
				.put("cluster.name", "elasticsearch").build();
		// �������ͬʱ���Ӽ�Ⱥ�ķ�����,���Զ��,�������ӷ����ǿɷ��ʵ�
		client = new TransportClient(settings).addTransportAddress(
				new InetSocketTransportAddress("192.168.9.140", 9300))
				.addTransportAddress(
						new InetSocketTransportAddress("host2", 9300));*/
		client  = TransportClient.builder().build();

	}

	public void close() {
		client.close();
	}
	//initialize source datas
	public void createIndex() {
		String id,title;
		IndexRequestBuilder requestBuilder = client.prepareIndex("blog","post2").setRefresh(true);
		for(int i=0;i<10;i++){
			
			id="id"+i;
			title="title"+i;
//			setting base search data
			requestBuilder.setSource(getBuilderJson(id, title)).execute().actionGet();
		}
	}
	
	public String getBuilderJson(String id,String title){
		String json="";
		//build json data
		try {
			XContentBuilder builder=XContentFactory.jsonBuilder().startObject();
			builder.field("id", id);
			builder.field("title",title);
			
			json = builder.endObject().string();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return json;
	}
	//operate search 
	public SearchHits search(String type,String query){
		
		SearchRequestBuilder searchRequestBuilder = client.prepareSearch("blog");
//		setting search type
		searchRequestBuilder.setTypes(type);
		
		// 1.SearchType.DFS_QUERY_THEN_FETCH = ��ȷ��ѯ
		// 2.SearchType.SCAN = ɨ���ѯ,����
		// 3.SearchType.COUNT = �����õĻ�,���ΪĬ��ֵ,���е��Լ�ȥ���԰�
		
		searchRequestBuilder.setSearchType(SearchType.DFS_QUERY_AND_FETCH);
//		ָ���ֶ�  query represent field
		//searchRequestBuilder.setQuery(QueryBuilders.inQuery("title", query));
		
		/*// ���ò�ѯ��ݵ�λ��,��ҳ�ð�
		searchRequestBuilder.setFrom(0);
		// ���ò�ѯ�����������
		searchRequestBuilder.setSize(60);
		// �����Ƿ񰴲�ѯƥ�������
		searchRequestBuilder.setExplain(true);
		// �����Ƿ���������Ӧ��Ϣ
*/				
		
		//return response
		SearchResponse response = searchRequestBuilder.execute().actionGet();
		SearchHit[] hits = response.getHits().hits();
		System.out.println(response.getScrollId());
		for(SearchHit hit : hits){
			
			 Map result = hit.getSource();
			/* Set<String> key = map.keySet();
		        for (Iterator it = key.iterator(); it.hasNext();) {
		            String s = (String) it.next();
		            System.out.println(map.get(s));
		        }*/
			 System.out.println(result);
			
		}
		

		System.out.println("search success");
		return response.getHits();
	}
	
	public void Get(){
		
		GetResponse response = client.prepareGet("blog", "post","1").execute().actionGet();
		
//		Map headers = response.getHeaders();
		System.out.println(response);
		
		boolean exists = response.isExists();
		System.out.println(exists);// �ж������Ƿ����
		
		String sourceString = response.getType();
		System.out.println(sourceString);// ��ȡ����,���Ҵ�ӡ����������
		
		String id = response.getId();
		System.out.println("id:"+id);// ��ȡ����id
	}
	
	public void delete(){
		//delete by id
		client.prepareDelete("blog", "post2", "AUqgxCxgtnbQoe74rzHP").execute();
		
		//delete by type
		MatchAllQueryBuilder allQueryBuilder = QueryBuilders.matchAllQuery();
//		client.prepareDeleteByQuery("blog").setQuery(allQueryBuilder).setTypes("post2").execute().actionGet();
		
		
		//delete by index
		DeleteIndexRequest request = new DeleteIndexRequest();
		client.admin().indices().delete(request);
		
		
	}
}
