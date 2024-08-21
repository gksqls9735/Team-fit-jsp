<%@page import="model.MealVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="controller.MealDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="top.jsp"%>
<%
MealDAO dao = MealDAO.getInstance();
ArrayList<MealVO> meallist = dao.getDiet();
int count = 0;
%>
<main>
	<section class="hero">
		<div class="slideshow">
          <div class="slideshow_slides">
            <a href="https://in-the-sky.org/skymap.php" target="_blank"
              ><img src="images/lecture.png" alt="지구와 달"
              style="width: 950px; height: 465px"
            /></a>
            <a href="https://www.nasa.gov/" target="_blank"
              ><img src="images/match.png" alt="탐사선"
              style="width: 950px; height: 465px"
            /></a>
            <a href="https://www.esa.int/" target="_blank"
              ><img src="images/lecture.png" alt="로켓"
              style="width: 950px; height: 465px"
            /></a>
            <a href="https://solarsystemscope.com/" target="_blank"
              ><img
                style="width: 950px; height: 465px"
                src="images/match.png"
                alt="우주시뮬레이터"
            /></a>
          </div>
          <div class="slideshow_nav">
            <a href="#" class="prev"><i class="fa-solid fa-angles-left"></i></a>
            <a href="#" class="next"
              ><i class="fa-solid fa-angles-right"></i
            ></a>
          </div>
          <div class="indicator">
            <a href="#" class="active"
              ><i class="fa-solid fa-circle-dot"></i
            ></a>
            <a href="#"><i class="fa-solid fa-circle-dot"></i></a>
            <a href="#"><i class="fa-solid fa-circle-dot"></i></a>
            <a href="#"><i class="fa-solid fa-circle-dot"></i></a>
          </div>
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
			<blockquote>"TeamFit has transformed my life!" - Sarah
				K.</blockquote>
			<blockquote>"The trainers are so supportive and
				knowledgeable." - Mike D.</blockquote>
		</div>
	</section>
	<section class="diet">
        <span class="legend-style">오늘의 추천메뉴</span>
        <div class="diet-content">
            <div class="carousel">
                <div class="carousel-inner">
                    <%
                    for(MealVO diet : meallist) {
                        String imgPath;
                        switch (count % 5) {
                            case 0:
                                imgPath = "images/rice.png";
                                count++;
                                break;
                            case 1:
                                imgPath = "images/fry.png";
                                count++;
                                break;
                            case 2:
                                imgPath = "images/jjigae.png";
                                count++;
                                break;
                            case 3:
                                imgPath = "images/fish.png";
                                count++;
                                break;
                            case 4:
                                imgPath = "images/steak.png";
                                count++;
                                break;
                            default:
                                imgPath = "images/salad.png";
                                count++;
                        }
                    %>
                    <div class="carousel-item">
                        <table class="meal-table">
                            <tr>
                                <td>메뉴</td>
                                <td><%= diet.getMeal_nm() %></td>
                            </tr>
                            <tr>
                                <td>레시피</td>
                                <td><%= diet.getCook_mth_cont() %></td>
                            </tr>
                            <tr>
                                <td>재료</td>
                                <td><%= diet.getMatrl_nm() %></td>
                            </tr>
                            <tr>
                                <td>영양분</td>
                                <td> 
                                    칼로리: <%= diet.getCalorie_qy() %><br/>
                                    단백질: <%= diet.getProtein_qy() %><br/>
                                    지방: <%= diet.getFat_qy() %><br/>
                                    섬유질: <%= diet.getCellu_qy() %><br/>
                                    칼슘: <%= diet.getCalcium_qy() %><br/>
                                    인: <%= diet.getPhosph_qy() %><br/>
                                    철분: <%= diet.getFe_qy() %><br/>
                                    나트륨: <%= diet.getNatrium_qy() %><br/>
                                    칼륨: <%= diet.getPotassium_qy() %><br/>
                                    비타민A: <%= diet.getVitamina_qy() %><br/>
                                    비타민B1: <%= diet.getThiamin_qy() %><br/>
                                    비타민B2: <%= diet.getRiboflamin_qy() %><br/>
                                    비타민B3: <%= diet.getNiacin_qy() %><br/>
                                    비타민C: <%= diet.getVitaminc_qy() %>
                                </td>
                            </tr>  
                        </table>
                        <div class="meal-image">
                            <img src="<%= imgPath %>" alt="<%= diet.getMeal_nm() %>">
                        </div>
                    </div>                      
                    <% } %>
                </div>
            </div>
        </div>
    </section>
</main>
<%@ include file="bottom.jsp"%>
