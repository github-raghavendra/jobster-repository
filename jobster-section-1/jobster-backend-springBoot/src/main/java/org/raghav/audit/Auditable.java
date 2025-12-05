package org.raghav.audit;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;

/* @MappedSuperclass annotation : 
 * To mark this class as a non-persistent class and hibernate 
 * will not create/look up a table mapping for this class.

  @EntityListeners(AuditingEntityListener.class):
  If you annotate an entity class with @EntityListeners, 
  you can specify one or more listener classes that implement the corresponding JPA callback methods.
  These methods will be invoked automatically when specified events occur on the entity, 
  such as entity creation, update, or deletion.

  AuditingEntityListener:
  is a built-in entity listener class provided by Spring Data JPA for auditing purposes. 
  It is used in conjunction with auditing annotations 
  (@CreatedBy, @LastModifiedBy, @CreatedDate, @LastModifiedDate) 
  to automatically populate auditing-related fields in entities.

 * 
 */
@MappedSuperclass
@EntityListeners({ AuditingEntityListener.class })
@Getter@NoArgsConstructor
public abstract class Auditable {

	@CreatedDate
	private LocalDateTime createdAt;

	@LastModifiedDate
	private LocalDateTime lastModifiedAt;

	@CreatedBy
	private String createdBy;

	@LastModifiedBy
	private String lastModifiedBy;

}
