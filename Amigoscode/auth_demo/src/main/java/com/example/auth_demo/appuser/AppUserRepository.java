package com.example.auth_demo.appuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

// A transaction is a very small unit of a program and it may contain several lowlevel tasks. A transaction in a database system must maintain
// Atomicity, Consistency, Isolation, and Durability − commonly known as ACID properties − in order to ensure accuracy, completeness, and data
// integrity.

// A transaction is a unit of work that you want to treat as "a whole." It has to either happen in full or not at all. A classical example is
// transferring money from one bank account to another. To do that you have first to withdraw the amount from the source account, and then
// deposit it to the destination account. The operation has to succeed in full. If you stop halfway, the money will be lost.

// Generally the @Transactional annotation is written at the service level. It is used to combine more than one writes on a database as a
// single atomic operation. When somebody call the method annotated with @Transactional all or none of the writes on the database is executed.
// In the case of read operations it is not useful and so it is in case of a single atomic write.

// Here @Transactional is used with the class, then all its methods will follow the transactional rule.
// ReadOnly: This just serves as a hint for the actual transaction subsystem; it will not necessarily cause failure of write access attempts.
// A transaction manager which cannot interpret the read-only hint will not throw an exception when asked for a read-only transaction. The fact
// is that we can't be sure that an insert or update won't occur when the readOnly flag is set. This behavior is vendor dependent, whereas JPA
// is vendor agnostic (agnostic means the one that doesn't believe in God, here it means JPA doesn't dependent on vendor).
@Repository
@Transactional(readOnly = true)
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByEmail(String email);

    // The @Modifying annotation is used to enhance the @Query annotation to execute not only SELECT queries but also INSERT, UPDATE, DELETE,
    // and even DDL queries.

    // As we can see, this method returns an integer. It's a feature of Spring Data JPA @Modifying queries that provides us with the number of
    // updated entities.
    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)    // By default both are set to false
    @Query("UPDATE AppUser a SET a.enabled = TRUE WHERE a.email = ?1")
    int enableAppUser(String email);
}