package dat3.security.entity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Configurable
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DISCRIMINATOR_TYPE")
public class UserWithRoles implements UserDetails {

  @Id
  String username;
  String email;
  //60 = length of a bcrypt encoded password
  @Column(nullable = false, length = 60)
  String password;
  private boolean enabled = true;
  @UpdateTimestamp
  private LocalDateTime created;
  @UpdateTimestamp
  private LocalDateTime edited;
  @Enumerated(EnumType.STRING)
  @Column(columnDefinition = "ENUM('USER','ADMIN')")
  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "security_role")
  List<Role> roles = new ArrayList<>();

  public UserWithRoles(String user, String password, String email){
    this.username = user;
    setPassword(password);
    this.email = email;
  }

  @Transient
  private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
  public void setPassword(String pw){
    this.password = passwordEncoder.encode(pw);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return roles.stream().map(role -> new SimpleGrantedAuthority(role.toString())).collect(Collectors.toList());
  }

  public void addRole(Role roleToAdd) {
    if (!roles.contains(roleToAdd)) {
      roles.add(roleToAdd);
    }
  }

  public void removeRole(Role roleToRemove) {
    roles.remove(roleToRemove);
  }

  @Override
  public boolean isAccountNonExpired() {return enabled;}
  @Override
  public boolean isAccountNonLocked() { return enabled;}
  @Override
  public boolean isCredentialsNonExpired() { return enabled; }
}