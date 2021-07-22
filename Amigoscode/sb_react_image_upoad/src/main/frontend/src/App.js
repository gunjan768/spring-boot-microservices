import React, { useCallback, useEffect, useState } from 'react';
import './App.css';
import axios from 'axios';
import {useDropzone} from 'react-dropzone'

function Dropzone({userProfileId}) {

	// console.log({userProfileId});

	const onDrop = useCallback(acceptedFiles => {
		
		const file = acceptedFiles[0];
		const formData = new FormData();
		
		// console.log("OnDrop : ", acceptedFiles);
		
		formData.append("file_key_name", file);

		axios.post(`http://localhost:8080/api/v1/user-profile/${userProfileId}/image/upload`, 
			formData,
			{
				headers: {
					"Content-Type": "multipart/form-data"
				}
			}
		).then(() => {

		}).catch(err => {

		});

	}, [userProfileId]);

	const {getRootProps, getInputProps, isDragActive} = useDropzone({onDrop})
  
	return (
		<div {...getRootProps()}>
			<input {...getInputProps()} />
			{ isDragActive ? <p>Drop the files here ...</p> : <p>Drag 'n' drop image, or click to select profile image</p> }
		</div>
	);
}

const UserProfiles = () => {
	
	const [userProfiles, setUserProfiles] = useState([]);

	const fetchUserProfiles = () => {
		axios.get("http://localhost:8080/api/v1/user-profile")
		.then(res => {
			console.log(res);
			setUserProfiles(res.data);
		})
	}

	useEffect(() => {
		fetchUserProfiles();
	}, []);

	return (
		userProfiles.map((userProfile, ind) => {
			return (
				<div key = {ind}>
					{
						userProfile.userProfileId ? 
							<img 
								src = {`http://localhost:8080/api/v1/user-profile/${userProfile.userProfileId}/image/download`}
								alt = {"https://www.indiewire.com/wp-content/uploads/2019/05/4-Helen-Sloan-HBO.jpg"}
							/>
						:
							null
					}
					<br /> <br />
					<h1>{userProfile.username}</h1>
					<p>{userProfile.userProfileId}</p>
					<Dropzone {...userProfile}/> 
					<br />
				</div>
			)
		})
	);
}

function App() {
	return (
		<div className="App">
			<UserProfiles />
		</div>
	);
}

export default App;