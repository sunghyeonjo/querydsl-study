package study.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.entity.Dummy;
import study.querydsl.entity.QDummy;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
class QuerydslApplicationTests {

	@Autowired
	EntityManager entityManager;

	@Test
	void contextLoads() {
		Dummy dummyEntity = new Dummy();
		entityManager.persist(dummyEntity);

		JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
		QDummy qDummyEntity = QDummy.dummy;

		Dummy result = queryFactory.selectFrom(qDummyEntity).fetchOne();

		Assertions.assertThat(result).isEqualTo(dummyEntity);
		Assertions.assertThat(result.getId()).isEqualTo(dummyEntity.getId());

	}
}
