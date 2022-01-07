package com.example.demo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.example.demo.model.Member;
import com.example.demo.model.MemberDto;
import com.example.demo.model.MemberType;
import com.example.demo.model.Team;


public class JqplStudy {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em =emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
tx.begin(); //transaction start
		
		try {
			
			Team teamA = new Team();
			teamA.setName("teamA");
			em.persist(teamA);
			
			Team teamB = new Team();
			teamB.setName("teamB");
			em.persist(teamB);
			
				
			Member member1 = new Member();
			member1.setUsername("member1");
			member1.setTeam(teamA);
			em.persist(member1);
			
			Member member2 = new Member();
			member2.setUsername("member2");
			member2.setTeam(teamA);
			em.persist(member2);
			
			Member member3 = new Member();
			member3.setUsername("member3");
			member3.setTeam(teamB);
			em.persist(member3);
			
		
			
			em.flush();
			em.clear();
			
			
			////// select ///////
//			TypedQuery<Member> query = em.createQuery("select m from Member m where m.username= :username",Member.class);
//			query.setParameter("username","hello");
//			Member singleRes = query.getSingleResult();
//			System.out.println("==========="+ singleRes.getUsername());
//
//			List<MemberDto> result2 = em.createQuery("select new com.example.demo.model.MemberDto(m.username,m.age) from Member m",MemberDto.class).getResultList();
//			System.out.println(result2);
			
			
			////// 페이징 ///////
//			List<Member> query =  em.createQuery("select m from Member m order by m.age desc",Member.class)
//					.setFirstResult(0)
//					.setMaxResults(10)
//					.getResultList();
//			
//			System.out.println("============="+query.size());
//			for(Member m : query) {
//				System.out.println(m.getUsername());
//			}
			
//			String query = "select m from Member m left join m.team t on t.name = 'teamA'";
//			String query = "select m from Member m left join m.team t on t.name = m.username"; // 연관관계 없는 join
//			List<Member> result =  em.createQuery(query,Member.class)
//					.getResultList();
			
			
//			String query = "select i from Item from i where type(i) = Book";
//			String query = "select m.username,'Hello',true from Member m where m.type = com.example.demo.model.MemberType.ADMIN";
			
//			String q = "select " +
//						"	case when m.age <= 10 then '학생요금'	" +
//						"	when m.age >= 60 then '경로요금'	" +
//						"	else '일반요금' end	" +
//						"	from Member m	" ;
//			String q = "select coalesce(m.username,'이름없는 회원') from Member m";
//			String q = "select  m from Member m join fetch m.team";
			
			
//			String q = "select distinct t from Team t join fetch t.members";
//			List<Team> res =  em.createQuery(q,Team.class).getResultList();
			
//			String q = "select t from Team t";
//			List<Team> res =  em.createQuery(q,Team.class)
//						.setFirstResult(0)
//						.setMaxResults(2)
//						.getResultList();
//			
//			for(Team s : res) {
//				System.out.println("Team = "+ s.getName()+ "  , "+ s.getMembers().size());
//			}

			List<Member> result = em.createNamedQuery("Member.findByUsername").setParameter("username", "member1").getResultList();
			
			for(Member m : result) {
				System.out.println("Member  :  " + m.getUsername());
			}
			tx.commit();
			
		} catch (Exception e) {
			tx.rollback();
		}finally {
			em.close();
		}
		
		emf.close();
	}

}
