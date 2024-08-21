<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>TeamFit</title>
    <link rel="stylesheet" href="styles_final.css" />
        <script src="scripts_final.js" defer></script>
  </head>
  <body>
    <header>
      <div class="logo">
        <img src="teamfit_logo.png" alt="TeamFit Logo" />
        <h1>TeamFit</h1>
      </div>
      <nav>
        <ul>
          <li><a href="#">공지사항</a></li>
          <li><a href="#">강의신청</a></li>
          <li><a href="#">강사매칭</a></li>
          <li><a href="#">고객센터</a></li>
        </ul>
      </nav>
    </header>
    <main>
      <section class="hero">
        <div class="hero-content">
          <h2>Class Classes & Personal Trainer Matching</h2>
          <p>
            We match you with amazing exercise classes and personal trainers.
          </p>
          <button>Get Started</button>
        </div>
        <div class="hero-image">
          <img src="hero_image.png" alt="Exercise" />
        </div>
      </section>
      <section class="details">
        <div class="detail-card">
          <h3>Class Schedules</h3>
          <ul>
            <li>Monday: Yoga - 8 AM</li>
            <li>Tuesday: Pilates - 10 AM</li>
            <li>Wednesday: HIIT - 6 PM</li>
          </ul>
        </div>
        <div class="detail-card">
          <h3>Trainer Profiles</h3>
          <ul>
            <li>Jane Doe - Yoga Expert</li>
            <li>John Smith - HIIT Specialist</li>
            <li>Emma Brown - Pilates Instructor</li>
          </ul>
        </div>
        <div class="detail-card">
          <h3>Testimonials</h3>
          <blockquote>"TeamFit has transformed my life!" - Sarah K.</blockquote>
          <blockquote>
            "The trainers are so supportive and knowledgeable." - Mike D.
          </blockquote>
        </div>
      </section>
      <section class="signup">
        <h3>Sign Up</h3>
        <form action="signup" method="post">
          <label for="name">Name:</label>
          <input type="text" id="name" name="name" required />
          <label for="email">Email:</label>
          <input type="email" id="email" name="email" required />
          <button type="submit">Sign Up</button>
        </form>
      </section>
    </main>
    <footer>
      <p>&copy; 2024 TeamFit. All rights reserved.</p>
    </footer>
  </body>
</html>
