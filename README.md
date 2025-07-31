# OnlineFoodOrderApp
# 🍽️ Spring Boot Food Order App Setup Guide

## ✅ 1. Create Project

1. Open **Eclipse**  
2. Go to: `File` → `New` → `Other` → Search for **Spring Starter Project**
<img width="640" height="622" alt="image" src="https://github.com/user-attachments/assets/5086b7b0-f988-4ac0-8070-556fdbd8b3af" />

4. Provide project details (e.g., **Project Name**, **Artifact ID**, etc.)
<img width="389" height="549" alt="image" src="https://github.com/user-attachments/assets/7f96ffd1-6970-41d2-9d45-ad8e4ec75d60" />



6. Add the following dependencies:
   - `Spring Web`
   - `Spring Boot DevTools`
   - `MySQL Driver`
   - `Spring Data JPA`
   - `Lombok`
  

<img width="690" height="966" alt="image" src="https://github.com/user-attachments/assets/75173431-acee-4b66-9303-ef95fc219efc" />



---

## 📁 2. Create the Following Package Structure

<img width="389" height="658" alt="image" src="https://github.com/user-attachments/assets/8a30289b-18be-4c29-b744-d056160d5fec" />


---

## ⚙️ 3. Lombok Setup

1. [Download Lombok JAR](https://projectlombok.org/download)
2. Run the JAR:
   - If the IDE path is not detected, **provide the path manually** (e.g., `Eclipse` installation path)
3. Click `Install` → Exit installer

<img width="1045" height="610" alt="Screenshot 2025-07-31 100439" src="https://github.com/user-attachments/assets/2babda8a-07d7-462e-ac99-81ff47fcdb88" />


4. Open Eclipse → Right-click your project → `Build Path` → `Configure Build Path`
   - Go to `Libraries` tab → Select `Classpath`
   - Click `Add External JARs...` → Select the downloaded Lombok JAR
   - Click `Apply and Close`

<img width="828" height="456" alt="image" src="https://github.com/user-attachments/assets/8ae1a072-f3a3-4efb-8dfe-6f879d8d86fe" />

---

<img width="1173" height="823" alt="image" src="https://github.com/user-attachments/assets/72c2cb1a-815e-431e-ab94-f6cd80da4a5b" />


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


