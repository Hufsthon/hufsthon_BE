package com.hufsthon.demo.auth.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;

import javax.annotation.processing.Generated;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;

/**
 * QMember is a Querydsl query type for Doctor
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

	public static final QMember member = new QMember("member1");
	private static final long serialVersionUID = 1123928256L;
	private static final PathInits INITS = PathInits.DIRECT2;
	public final com.hufsthon.demo.global.common.QBaseEntity _super = new com.hufsthon.demo.global.common.QBaseEntity(
		this);

	//inherited
	public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

	public final StringPath email = createString("email");

	public final NumberPath<Long> id = createNumber("id", Long.class);

	public final StringPath password = createString("password");

	public final com.hufsthon.demo.auth.refreshtoken.entity.QRefreshToken refreshToken;

	public final EnumPath<UserRole> role = createEnum("role", UserRole.class);

	//inherited
	public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

	public QMember(String variable) {
		this(Member.class, forVariable(variable), INITS);
	}

	public QMember(Path<? extends Member> path) {
		this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
	}

	public QMember(PathMetadata metadata) {
		this(metadata, PathInits.getFor(metadata, INITS));
	}

	public QMember(PathMetadata metadata, PathInits inits) {
		this(Member.class, metadata, inits);
	}

	public QMember(Class<? extends Member> type, PathMetadata metadata, PathInits inits) {
		super(type, metadata, inits);
		this.refreshToken = inits.isInitialized("refreshToken") ?
			new com.hufsthon.demo.auth.refreshtoken.entity.QRefreshToken(forProperty("refreshToken"),
				inits.get("refreshToken")) : null;
	}

}

