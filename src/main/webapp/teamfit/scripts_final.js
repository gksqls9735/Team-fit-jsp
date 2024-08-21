function call_js() {
  // 사진 및 앵커 참조변수 선언
  let slideshow = document.querySelector(".slideshow");
  let slideshow_slides = document.querySelector(".slideshow_slides");
  let slideshow_slides_a = document.querySelectorAll(".slideshow_slides a");

  // 화살표 객체 참조 변수 선언
  let prev = document.querySelector(".prev");
  let next = document.querySelector(".next");

  // 인디케이터 객체 참조 변수 선언
  let indicators = document.querySelectorAll(".indicator a");

  // 회전목마 현재 위치 값
  let currentIndex = 0;
  let timer = null;
  let slideCount = slideshow_slides_a.length;

  // 이미지 우측 배치
  for (let i = 0; i < slideCount; i++) {
    let newLeft = `${i * 100}%`;
    slideshow_slides_a[i].style.left = newLeft;
  }

  // 회전목마 움직이기 slideshow_slides 왼쪽으로 이동
  function gotoSlide(index) {
    currentIndex = index;
    let newLeft = `${index * -100}%`;
    slideshow_slides.style.left = newLeft;

    // 인디케이터 초기화 active를 지워주고 현재 인디케이터에 active 추가
    indicators.forEach((e) => {
      e.classList.remove("active");
    });
    indicators[currentIndex].classList.add("active");
  }

  // 0~사진개수 -1까지 3초 딜레이 시키며 gotoSlide 호출
  gotoSlide(0);
  function startTime() {
    timer = setInterval(() => {
      let index = (currentIndex + 1) % slideCount;
      gotoSlide(index);
    }, 3000);
  }
  startTime();

  // 이벤트 처리 마우스 올리면 멈추기 내리면 다시 회전 시작
  slideshow.addEventListener("mouseenter", function () {
    clearInterval(timer);
  });
  slideshow.addEventListener("mouseleave", function () {
    startTime();
  });

  // 화살표 이벤트 처리
  prev.addEventListener("mouseover", function () {
    clearInterval(timer);
  });
  next.addEventListener("mouseover", function () {
    clearInterval(timer);
  });
  prev.addEventListener("click", (e) => {
    // a의 기능을 막아줌
    e.preventDefault();
    currentIndex -= 1;
    // currentIndex가 0보다 작아지면 제일 마지막 사진부터 시작
    if (currentIndex < 0) {
      currentIndex = slideCount - 1;
    }
    gotoSlide(currentIndex);
  });
  next.addEventListener("click", (e) => {
    // a기능 막기
    e.preventDefault();
    currentIndex += 1;
    // currentIndex가 slide의 개수의 length와 같아지면 처음으로 사진으로 돌아가기
    if (currentIndex > slideCount - 1) {
      currentIndex = 0;
    }
    gotoSlide(currentIndex);
  });
  indicators.forEach((e) => {
    e.addEventListener("mouseenter", () => {
      clearInterval(timer);
    });
  });
  for (let i = 0; i < indicators.length; i++) {
    indicators[i].addEventListener("click", (e) => {
      e.preventDefault();
      gotoSlide(i);
    });
  }

  // Carousel 자동 이동 기능 추가
  const carousel = document.querySelector(".carousel-inner");
  const items = document.querySelectorAll(".carousel-item");
  const itemWidth = items[0].offsetWidth;

  let carouselIndex = 0;
  let carouselTimer = null;

  function moveCarousel(index) {
    carouselIndex = index;
    let newLeft = `${index * -itemWidth}px`;
    carousel.style.transform = `translateX(${newLeft})`;
  }

  function startCarouselTimer() {
    carouselTimer = setInterval(() => {
      let index = (carouselIndex + 1) % items.length;
      moveCarousel(index);
    }, 5000);
  }

  moveCarousel(0);
  startCarouselTimer();

  carousel.addEventListener("mouseenter", function () {
    clearInterval(carouselTimer);
  });

  carousel.addEventListener("mouseleave", function () {
    startCarouselTimer();
  });
}

// 초기 로드 시 첫 번째 아이템 활성화
document.addEventListener("DOMContentLoaded", () => {
  call_js();
});



function idDBCheck(memberID) {
	if (memberID == "") {
		alert("아이디를 입력해 주세요.");
		document.mysignin.memberID.focus();
	} else {
		url = "myIdCheck.jsp?memberID=" + memberID;
		window.open(url, "post", "width=500,height=300, top=500, left=300");
	}
}
function closeWindow(flag) {
	if (flag == 1) {
		opener.document.getElementById('memberID').value = '';
	}
	window.close();
}
function idCheck() {
	const memberID = document.querySelector("#memberID");
	const memberIDInfo = document.querySelector("#memberIDInfo");
	let idExp = /^[a-z]+[a-z0-9]{5,19}$/g;

	if (!memberID.value.match(idExp)) {
		memberIDInfo.innerHTML = `영소문자 또는 0~9까지 5~19자 사이로 연속되어있어야 합니다.`;
		return false;
	} else {
		memberIDInfo.innerHTML = `유효한 아이디입니다.`;
		return true;
	}
}
function pwCheck() {
	const memberPW = document.querySelector("#memberPW");
	const memberPWInfo = document.querySelector("#memberPWInfo");
	let pwExp = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/;

	if (!memberPW.value.match(pwExp)) {
		memberPWInfo.innerHTML = `영어, 숫자, 특수문자를 포함하여 8~16자 입력해주세요`;
		return false;
	} else {
		memberPWInfo.innerHTML = `유효한 비밀번호입니다.`;
		return true;
	}
}
function pwComCheck() {
	const pwComfirm = document.querySelector("#pwComfirm");
	const pwComfirmInfo = document.querySelector("#pwComfirmInfo");
	const memberPW = document.querySelector("#memberPW")

	if (!pwComfirm.value.match(memberPW.value)) {
		pwComfirmInfo.innerHTML = `비밀번호가 일치하지 않습니다.`;
		return false;
	} else {
		pwComfirmInfo.innerHTML = `비밀번호가 일치합니다`;
		return true;
	}
}
function nameCheck() {
	const memberName = document.querySelector("#memberName");
	const memberNameInfo = document.querySelector("#memberNameInfo");
	let nameExp = /^[가-힣]{2,4}$/;

	if (!memberName.value.match(nameExp)) {
		memberNameInfo.innerHTML = `띄어쓰기 없이 입력, 반드시 실명이어야 합니다.`;
		return false;
	} else {
		memberNameInfo.innerHTML = `GOOD`;
		return true;
	}
}
function emailCheck() {
	const memberEmail = document.querySelector("#memberEmail");
	const memberEmailInfo = document.querySelector("#memberEmailInfo");
	let emailExp = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i;

	if (!memberEmail.value.match(emailExp)) {
		memberEmailInfo.innerHTML = `이메일 형식에 맞게 입력해주세요.`;
		return false;
	} else {
		memberEmailInfo.innerHTML = `GOOD`;
		return true;
	}
}
function postCheck() {
	const postcode = document.querySelector("#postcode");
	const postcodeInfo = document.querySelector("#postcodeInfo");
	let postcodeExp = /\d{5}/;

	if (!postcode.value.match(postcodeExp)) {
		postcodeInfo.innerHTML = `숫자 5개를 입력해주세요.`;
		return false;
	} else {
		postcodeInfo.innerHTML = `GOOD`;
		return true;
	}
}
function phoneCheck() {
	const phoneNum = document.querySelector("#phoneNum");
	const phoneNumInfo = document.querySelector("#phoneNumInfo");
	let phoneExp = /^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}$/g;

	if (!phoneNum.value.match(phoneExp)) {
		phoneNumInfo.innerHTML = `-, 숫자(0~9)를 포함하여 형식에 맞게 입력해주세요`;
		return false;
	} else {
		phoneNumInfo.innerHTML = `GOOD`;
		return true;
	}
}
function allCheck(event) {
	if (idCheck() && pwCheck() && pwComCheck() && nameCheck()
		&& phoneCheck() && emailCheck() && postCheck()) {
		alert("회원가입 성공");
	} else if (!idCheck()) {
		document.querySelector("#memberID").focus();
		alert("입력한 아이디를 확인해주세요.");
		event.preventDefault();
	} else if (!pwCheck()) {
		document.querySelector("#memberPW").focus();
		alert("입력한 비밀번호를 확인해주세요");
		event.preventDefault();
	} else if (!pwComCheck()) {
		document.querySelector("#pwComfirm").focus();
		alert("비밀번호를 확인해주세요.");
		event.preventDefault();
	} else if (!nameCheck()) {
		document.querySelector("#memberName").focus();
		alert("입력한 이름을 확인해주세요.");
		event.preventDefault();
	} else if (!emailCheck()) {
		document.querySelector("#memberEmail").focus();
		alert("입력한 이메일 확인해주세요.");
		event.preventDefault();
	} else if (!postCheck()) {
		document.querySelector("#postcode").focus();
		alert("입력한 우편번호를 확인해주세요.");
		event.preventDefault;
	} else if (!phoneCheck()) {
		document.querySelector("#phoneNum").focus();
		alert("입력한 전화번호를 확인해주세요.");
		event.preventDefault();
	}
}
function updateCheck(event) {
	if (pwCheck() && pwComCheck() && nameCheck()
		&& phoneCheck() && emailCheck() && postCheck()) {
		document.signup.submit();
	} else if (!pwCheck()) {
		document.querySelector("#memberPW").focus();
		alert("입력한 비밀번호를 확인해주세요");
		event.preventDefault();
	} else if (!pwComCheck()) {
		document.querySelector("#pwComfirm").focus();
		alert("비밀번호를 확인해주세요.");
		event.preventDefault();
	} else if (!nameCheck()) {
		document.querySelector("#memberName").focus();
		alert("입력한 이름을 확인해주세요.");
		event.preventDefault();
	} else if (!emailCheck()) {
		document.querySelector("#memberEmail").focus();
		alert("입력한 이메일 확인해주세요.");
		event.preventDefault();
	} else if (!postCheck()) {
		document.querySelector("#postcode").focus();
		alert("입력한 우편번호를 확인해주세요.");
		event.preventDefault;
	} else if (!phoneCheck()) {
		document.querySelector("#phoneNum").focus();
		alert("입력한 전화번호를 확인해주세요.");
		event.preventDefault();
	}
}
function idSearchCheck(event) {
	if (nameCheck() && phoneCheck()) {
		document.myIdSearch.submit();
	} else if (!nameCheck()) {
		document.querySelector("#memberPW").focus();
		alert("입력한 비밀번호를 확인해주세요");
		event.preventDefault();
	} else if (!phoneCheck()) {
		document.querySelector("#phoneNum").focus();
		alert("입력한 전화번호를 확인해주세요.");
		event.preventDefault();
	}
}
function pwSearchCheck(event) {
	if (idCheck() && nameCheck() && phoneCheck()) {
		document.myPwSearch.submit();
	} else if (!idCheck()) {
		document.querySelector("#memberID").focus();
		alert("입력한 아이디를 확인해주세요.");
		event.preventDefault();
	} else if (!nameCheck()) {
		document.querySelector("#memberName").focus();
		alert("입력한 이름을 확인해주세요.");
		event.preventDefault();
	} else if (!phoneCheck()) {
		document.querySelector("#phoneNum").focus();
		alert("입력한 전화번호를 확인해주세요.");
		event.preventDefault();
	}
}
function postSearch() {
	new daum.Postcode({
		oncomplete: function(data) {
			// 콘솔 로그를 사용하여 data 객체의 내용을 확인
			console.log('주소 데이터:', data);

			var addr = ''; // 주소 변수
			var extraAddr = ''; // 참고항목 변수

			// 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
			if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
				addr = data.roadAddress;
			} else { // 사용자가 지번 주소를 선택했을 경우(J)
				addr = data.jibunAddress;
			}

			// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
			if (data.userSelectedType === 'R') {
				// 법정동명이 있을 경우 추가한다. (법정리는 제외)
				// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
				if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
					extraAddr += data.bname;
				}
				// 건물명이 있고, 공동주택일 경우 추가한다.
				if (data.buildingName !== '' && data.apartment === 'Y') {
					extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
				}
				// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
				if (extraAddr !== '') {
					extraAddr = ' (' + extraAddr + ')';
				}
			}

			// 조합된 참고항목을 해당 필드에 넣는다.
			document.getElementById("extraAddress").value = extraAddr;

			// 우편번호와 주소 정보를 해당 필드에 넣는다.
			document.getElementById("postcode").value = data.zonecode;
			document.getElementById("address").value = addr;

			// 커서를 상세주소 필드로 이동한다.
			document.getElementById("detailAddress").focus();
		}
	}).open();
}

/* 글쓰기 */
function writeSave() {
	if (document.writeForm.writer.value == "") {
		alert("작성자를 입력하십시요.");
		document.writeForm.writer.focus();
		return false;
	}
	if (document.writeForm.subject.value == "") {
		alert("제목을 입력하십시요.");
		document.writeForm.subject.focus();
		return false;
	}
	if (document.writeForm.content.value == "") {
		alert("내용을 입력하십시요.");
		document.writeForm.content.focus();
		return false;
	}

	if (document.writeForm.pass.value == "") {
		alert(" 비밀번호를 입력하십시요.");
		document.writeForm.pass.focus();
		return false;
	}
}
/*삭제글 암호 점검 */
function deleteSave() {
	if (document.delForm.pass.value == '') {
		alert("비밀번호를 입력하십시요.");
		document.delForm.pass.focus();
		return false;
	}
}
