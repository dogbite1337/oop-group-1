<template>
	<div class="backgroundDiv" @click="clickedMe">
		<router-link class="HomeLink" to="/">
			<div class="backHomeDiv">
				<div class="SpaceDiv" />
				<img class="ArrowIcon" src="../projectImages/white_arrow.png" />
				<div class="SpaceDiv" />
				<p class="HomeText">Home</p>
				<div class="SpaceDiv" />
				<img class="catIcon" src="../projectImages/whiteCatBlackBaground.png" />
				<div class="SpaceDiv" />
			</div>
		</router-link>
		<div v-if="!hideEyes" class="girlsOpenEyesDiv" />
		<div v-if="hideEyes" class="girlsClosedEyesDiv" />
		<div class="upScaleDiv">
			<div class="inputGrid">
				<div class="emailDiv">
					<p class="UsernameText">Username</p>
					<input
						v-model="wantedUserName"
						class="emailInput"
						type="text"
						placeholder="Please enter your email"
					/>
				</div>
				<div class="lineDiv" />
				<div class="passwordDiv">
					<p class="PasswordText">Password</p>
					<input
						v-model="wantedPassword"
						class="passwordInput"
						type="password"
						placeholder="Please enter your password"
					/>
					<div v-if="wantedPassword != ''" class="xDiv">
						<p class="Xsymbol">X</p>
					</div>
				</div>
				<div class="lineDiv" />
				<div v-if="wantedPassword == ''" class="sevenPixelsLineDiv" />
				<div v-if="wantedPassword != ''" class="fivePixelsLineDiv" />
				<div class="DescriptionDiv">
					<p class="UsernameText">Description</p>
					<input
						v-model="wantedDescription"
						class="descriptionInput"
						type="textarea"
						placeholder="Tell us about yourself (Optional)"
					/>
				</div>
			</div>
			<div class="RegisterContent">
				<div class="RegisterDiv">
					<div />
					<div class="PreviewImageDiv">
						<p class="previewText">Preview</p>
						<img
							class="PreviewImage"
							:src="`${currentImageURL == '' ? baseImage : currentImageURL}`"
						/>
					</div>
					<div />
					<div class="ImageURLInputDiv">
						<input
							v-model="wantedImageURL"
							class="imageURLInput"
							type="text"
							placeholder="Image url goes here.."
						/>
						<input
							@click="uploadPreviewPicture"
							v-if="canUpload"
							class="uploadImageButton"
							type="button"
							value="Upload Profile Image"
						/>
						<input
							v-if="!canUpload"
							class="disasbledUploadImageButton"
							type="button"
							value="Upload Profile Image"
						/>
						<input
							v-if="!canRegister"
							class="DisabledRegisterButton"
							type="button"
							value="Register"
						/>
						<input
							@click="registerUser"
							v-if="canRegister"
							class="RegisterButton"
							type="button"
							value="Register"
						/>
					</div>
				</div>
			</div>
			<div />
		</div>
	</div>
	<Footer />
</template>
<script>
import User from "../jsClasses/general/User";
import Footer from "../components/Footer.vue";

export default {
	name: "RegisterPage",
	components: {
		Footer,
	},
	data() {
		return {
			canRegister: false,
			hideEyes: "",
			wantedUserName: "",
			wantedPassword: "",
			wantedImageURL: "",
			currentImageURL: "",
			wantedDescription: "",
			baseImage: "src\\projectImages\\Dark_User.png",
			canUpload: false,
		};
	},
	watch: {
		currentImageURL() {
			if (this.wantedUserName.length > 0 && this.wantedPassword.length > 0) {
				this.canRegister = true;
			}
		},
		wantedImageURL() {
			if (this.wantedImageURL.length > 0) {
				this.canUpload = true;
			} else {
				this.canUpload = false;
			}
		},
		wantedUserName() {
			if (
				this.wantedUserName.length > 0 &&
				this.wantedPassword.length > 0 &&
				this.currentImageURL.length > 0
			) {
				this.canRegister = true;
			} else {
				this.canRegister = false;
			}
		},
		wantedPassword() {
			if (this.wantedUserName.length > 0 && this.wantedPassword.length > 0) {
				this.canLogIn = true;
			} else {
				this.canLogIn = false;
			}
		},
	},
	methods: {
		uploadPreviewPicture() {
			this.currentImageURL = this.wantedImageURL;
		},
		async registerUser() {
			let user = {
				providedUserName: this.wantedUserName,
				providedPassword: this.wantedPassword,
				providedDescription: this.wantedDescription,
				providedProfileURL: this.currentImageURL,
			};
			let res = await fetch("/api/register", {
				method: "POST",
				body: JSON.stringify(user),
			});

			let response = await res.json();
			if (response == null) {
				//Failed to Register
				alert("Error - Failed to Register, Username already taken!");
			} else {
				let currentUser = new User();
				user = Object.assign(currentUser, response);
				this.$store.dispatch("login", user);
				document.getElementsByClassName("HomeLink")[0].click();
			}
		},
		clickedMe(e) {
			if (e.target.className == "passwordInput") {
				this.hideEyes = true;
			} else {
				this.hideEyes = false;
				if (e.target.className == "Xsymbol") {
					this.wantedPassword = "";
				}
			}
		},
	},
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Revalia&family=Roboto&display=swap");
@import url("https://fonts.googleapis.com/css2?family=Revalia&family=Roboto:wght@300;400&display=swap");

* {
	outline: none;
	border: none;
}
.HomeLink {
	text-decoration: none;
}
.imageURLInput,
.uploadImageButton,
.registerButton {
	display: block;
}
.RegisterContent {
	width: max-content;
	margin-left: auto;
	margin-right: auto;
}
.uploadImageButton,
.registerButton {
	margin-top: 9px;
}
.imageURLInput {
	width: 243px;
	padding-left: 5px;
	border: 0.7px solid #ffffff;
	padding-top: 1px;
	height: 21px;
	margin-top: 3px;
	margin-right: 19px;
}
.uploadImageButton {
	width: 192px;
	color: white;
	font-size: 16px;
	font-family: "Roboto", sans-serif;
	background-color: #2d2c2c;
	border: 0.7px solid #ffffff;
}
.disasbledUploadImageButton {
	display: block;
	margin-top: 9px;
	width: 192px;
	color: rgba(255, 255, 255, 0.45);
	font-size: 16px;
	font-family: "Roboto", sans-serif;
	background: rgba(45, 44, 44, 0.5);
	border: 0.7px solid rgba(255, 255, 255, 0.45);
	box-sizing: border-box;
	box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
}
.RegisterButton {
	width: 142px;
	color: white;
	font-size: 18px;
	line-height: 21px;
	font-family: "Roboto", sans-serif;
	margin-top: 10px;
	height: 40px;
	background: #2d2c2c;
	border: 0.7px solid #ffffff;
	box-sizing: border-box;
	box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
}
.DisabledRegisterButton {
	width: 142px;
	font-size: 18px;
	line-height: 21px;
	font-family: "Roboto", sans-serif;
	color: rgba(255, 255, 255, 0.45);
	margin-top: 10px;
	height: 40px;
	background: rgba(45, 44, 44, 0.5);
	border: 0.7px solid rgba(255, 255, 255, 0.45);
	box-sizing: border-box;
	box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
}
.previewText {
	color: white;
	margin-bottom: 3px;
	text-align: center;
}
.xDiv {
	color: white;
	width: 25px;
	height: 22px;
	z-index: 500;
	text-align: center;
	padding-top: 3px;
	position: relative;
	margin-left: 117%;
	margin-top: -23px;
	border-radius: 50px;
	background-color: #c4c4c4;
}

.sevenPixelsLineDiv {
	height: 7px;
}
.fivePixelsLineDiv {
	height: 5px;
}
.PreviewImage {
	width: 80px;
	height: 80px;
	border-radius: 60px;
}
.PreviewImageDiv {
	width: max-content;
	padding-right: 10px;
}
.RegisterDiv {
	display: grid; /* space, preview, space, input, space */
	grid-template-columns: 33px auto 0px auto 8px;
}
.UsernameText,
.PasswordText {
	display: inline;
	color: white;
	font-family: "Roboto", sans-serif;
	font-style: normal;
	font-weight: normal;
	font-size: 18px;
	line-height: 21px;
}
.forgotPasswordDiv {
	text-align: center;
	font-family: "Roboto", sans-serif;
	font-style: normal;
	font-weight: normal;
	font-size: 18px;
	line-height: 21px;
	/* identical to box height */
	text-align: center;
	color: #eb6a6a;
	margin-top: 23px;
}

.backgroundDiv {
	background-color: black;
	height: 100vh;
}
.lineDiv {
	height: 1px;
	background-color: white;
	width: 350px;
	position: relative;
	z-index: 10;
	margin-left: auto;
	margin-right: auto;
}
.descriptionInput {
	display: inline;
	position: absolute;
	width: 214.5px;
	margin-left: -115.5px;
	padding-left: 129px;
	background-color: #2d2c2c;
	height: 46px;
	padding-bottom: 64px;
	margin-top: -14px;
	padding-top: 3px;
	color: white;
} /* 170.425 */
.emailInput {
	display: inline;
	position: absolute;
	width: 214.5px;
	margin-left: -115.5px;
	padding-left: 129px;
	background-color: #2d2c2c;
	height: 46px;
	margin-top: -16px;
	padding-top: 5px;
	color: white;
} /* 170.425 */
.passwordInput {
	display: inline;
	position: absolute;
	color: white;
	width: 170px;
	margin-left: -145px;
	padding-left: 129px;
	padding-right: 44px;
	background-color: #2d2c2c;
	height: 50px;
	margin-top: -16px;
	padding-top: 3px;
	outline: none;
}
.UsernameText {
	z-index: 3;
	position: relative;
	margin-top: 12.5px;
	font-size: 18px;
	font-weight: 400;
	font-family: "Roboto", sans-serif;
	line-height: 21.06px;
}
.PasswordText {
	padding-right: 32px;
	z-index: 3;
	position: relative;
	font-size: 18px;
	font-weight: 400;
	font-family: "Roboto", sans-serif;
	line-height: 21.06px;
}
.emailDiv {
	margin-top: 10px;
}
.passwordDiv {
	margin-bottom: 12px;
}
.DescriptionDiv {
	display: block;
	width: 292px;
	margin-left: auto;
	margin-right: auto;
	margin-bottom: 100px;
}
.emailDiv,
.passwordDiv {
	width: 232px;
	margin-left: auto;
	margin-right: auto;
	outline: 1px solid #2d2c2c;
	padding-top: 15px;
	padding-bottom: 15px;
	padding-left: 6px;
}

.passwordDiv {
	padding-right: 47px;
	margin-top: 3px;
}
.emailDiv {
	padding-right: 47px;
}
.girlsOpenEyesDiv {
	height: 140px;
	text-align: center;
	background-image: url("../projectImages/open eyes.PNG");
	background-size: 100% 140px;
	background-repeat: no-repeat;
	margin-left:40px;
margin-right:40px;
margin-top:20px;
}
.girlsClosedEyesDiv {
	height: 140px;
	text-align: center;
	background-image: url("../projectImages/cover eyes.PNG");
	background-size: 100% 140px;
	background-repeat: no-repeat;
margin-left:40px;
margin-right:40px;
margin-top:20px;
}
.backHomeDiv {
	display: grid; /* space, Arrow, space, Home, space, Cat */
	grid-template-columns: 17px 32px 5px 60px 12px 20px auto;
	background-color: black;
}
.ArrowIcon {
	transform: rotate(180deg);
	margin-top: 20px;
	margin-bottom: 12px;
	height: 25px;
	width: 28px;
}
.HomeText {
	font-size: 24px;
	font-weight: 500;
	font-family: "Roboto", sans-serif;
	line-height: 28.13px;
	margin-top: 18px;
	color: white;
	margin-bottom: 15px;
}
.catIcon {
	width: 28px;
	height: 31px;
	margin-top: 15px;
	margin-bottom: 12px;
}

.inputGrid {
	display: grid;
	background-color: #2d2c2c;
	width: 350px;
	margin-left: auto;
	margin-right: auto;
	margin-top: 60px;
	margin-bottom: 60px;
}

@media screen and (min-width: 550px) {
	.girlsOpenEyesDiv,
	.girlsClosedEyesDiv {
		width: 460px;
		margin-left: auto;
		margin-right: auto;
		margin-top: 30px;
		margin-bottom: 30px;
		height: 200px;
		background-size: 100% 200px;
	}
		.upScaleDiv {
		transform: scale(1.1, 1.1);
	}
}

@media screen and (min-width: 700px) {
	.girlsOpenEyesDiv,
	.girlsClosedEyesDiv {
		width: 580px;
		margin-left: auto;
		margin-right: auto;
		margin-top: 40px;
		margin-bottom: 40px;
		height: 240px;
		background-size: 100% 240px;
	}
	.upScaleDiv {
		transform: scale(1.2, 1.1);
	}
}

@media screen and (min-width: 850px) {
	.girlsOpenEyesDiv,
	.girlsClosedEyesDiv {
		width: 640px;
		margin-left: auto;
		margin-right: auto;
		margin-top: 50px;
		margin-bottom: 60px;
		height: 300px;
		background-size: 100% 300px;
	}
	.upScaleDiv {
		transform: scale(1.3, 1.1);
	}
}
</style>
