# OnlineFoodOrderApp
# 🍽️ Spring Boot Food Order App Setup Guide

## ✅ 1. Create Project

1. Open **Eclipse**  
2. Go to: `File` → `New` → `Other` → Search for **Spring Starter Project**
3. Provide project details (e.g., **Project Name**, **Artifact ID**, etc.)
4. Add the following dependencies:
   - `Spring Web`
   - `Spring Boot DevTools`
   - `MySQL Driver`
   - `Spring Data JPA`
   - `Lombok`

---

## 📁 2. Create the Following Package Structure

<img width="389" height="658" alt="image" src="https://github.com/user-attachments/assets/8a30289b-18be-4c29-b744-d056160d5fec" />


---

## ⚙️ 3. Lombok Setup

1. [Download Lombok JAR](https://projectlombok.org/download)
2. Run the JAR:
   - If the IDE path is not detected, **provide the path manually** (e.g., `Eclipse` installation path)
3. Click `Install` → Exit installer  
4. Open Eclipse → Right-click your project → `Build Path` → `Configure Build Path`
   - Go to `Libraries` tab → Select `Classpath`
   - Click `Add External JARs...` → Select the downloaded Lombok JAR
   - Click `Apply and Close`
5. **Restart IDE** (One-time setup)

---

## 🧾 4. Entity Class Setup

### 🔹 Class-Level Annotations
```java
@Entity                      // jakarta.persistence.Entity
@Data                        // lombok.Data
@AllArgsConstructor          // lombok.AllArgsConstructor
@NoArgsConstructor           // lombok.NoArgsConstructor
```
### 🔹 Field-Level Annotations
```java
@Id                          // jakarta.persistence.Id
@GeneratedValue(strategy = GenerationType.AUTO)
```


