# 🩺 AI-Powered Health Diagnostic System

A full-stack, containerized AI system that provides preliminary health assessments based on user-reported symptoms — powered by Machine Learning, Spring Boot, Flask, React, and Docker.

---

## 🚀 Live Demo

Coming Soon via [Render](https://render.com/) deployment!

---

## 📸 Screenshots

<img src="screenshots/register.png" width="400"/>
<img src="screenshots/predict.png" width="400"/>

---

## 🧠 Problem Statement

Many individuals ignore minor health issues due to lack of awareness or limited access to healthcare professionals.  
This system helps with **preliminary health assessments** based on symptoms and recommends next steps — potentially preventing serious outcomes.

---

## 🛠️ Tech Stack

| Layer        | Stack                                   |
|--------------|------------------------------------------|
| Frontend     | React + Tailwind CSS                     |
| Backend      | Spring Boot (Java) + JWT Auth            |
| ML Service   | Flask + Python + Scikit-learn            |
| Data Layer   | MongoDB (via Atlas or Docker)            |
| DevOps       | Docker + Docker Compose + Render         |

---

## 🔧 Features

✅ Symptom Checker (ML-based)  
✅ User Authentication (JWT-secured)  
✅ Conditional Diagnosis with Confidence %  
✅ Dockerized Flask, Spring Boot & React  
✅ Role-based UI rendering  
✅ Production-ready with React static site

---

## 🧪 Local Development

### 1. Clone the Repo

```bash
git clone https://github.com/your-username/health-diagnostic-system.git
cd health-diagnostic-system

### 2. Build and Run All Services (Docker Compose)
docker compose up --build

Then access:
	•	React UI → http://localhost:3000
	•	Spring Boot Backend → http://localhost:8080
	•	Flask ML API → http://localhost:5000

📁 Project Structure
.
├── flask-api/            # ML model in Flask
├── HDS-Backend/backend/  # Spring Boot + MongoDB
├── HDS-Frontend/frontend # React UI
├── docker-compose.yml    # Multi-service Docker config

📦 API Endpoints
🔐 Auth
	•	POST /auth/register
	•	POST /auth/login

🧠 ML Model (Flask)
	•	POST /predict
Payload:
{ "symptoms": [1, 4, 3, 0, 0, 0, ...] }

🧾 Response:
{
  "predicted_disease": "Acne",
  "confidence_score": "92%",
  "alternative_predictions": [...]
}

✨ Future Enhancements
	•	Role-based access for doctors/patients
	•	Connect wearable health data (Fitbit/Apple Health)
	•	Push notifications via Firebase
	•	Admin analytics dashboard

🧑‍💻 Author

Made with 💖 by Bhavna Kumari

📄 License

MIT License. Feel free to fork, star ⭐, and contribute!
---

Let me know if you'd like:
- A version with badges (`Build Passing`, `Docker`, `License`, etc.)
- A version that includes `.render.yaml` for CI/CD deployment
- PDF download or markdown preview

Ready to publish this beauty to the world? 🌍✨
