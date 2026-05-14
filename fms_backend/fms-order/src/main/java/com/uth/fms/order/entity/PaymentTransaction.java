package com.uth.fms.order.entity;

import com.uth.fms.common.enums.PaymentMethod;
import com.uth.fms.common.enums.PaymentType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment_transactions")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", nullable = false)
	Order order;

	@Enumerated(EnumType.STRING)
	@Column(name = "type", nullable = false, length = 20)
	PaymentType type;

	@Enumerated(EnumType.STRING)
	@Column(name = "method", nullable = false, length = 20)
	PaymentMethod method;

	@Column(name = "amount", nullable = false, precision = 15, scale = 2)
	BigDecimal amount;

	@Column(name = "receipt_image_url", length = 500)
	String receiptImageUrl;

	@Column(name = "recorded_by", nullable = false)
	Long recordedBy;

	@CreatedDate
	@Column(name = "created_at", updatable = false)
	LocalDateTime createdAt;
}
