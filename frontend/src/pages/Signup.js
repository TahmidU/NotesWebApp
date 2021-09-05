import React, { useState } from 'react';
import { useHistory } from 'react-router';
import '../styles/theme.css';
import '../styles/signup.css';
import '../styles/text.css';
import '../styles/button.css';
import LogoImg from '../img/logo_without_text.svg';
import { Button } from '../components/Button';
import axios from 'axios';
import { EditText } from '../components/EditText';
import { nameValidation, usernameValidation, emailValidation, passwordValidation } from '../util/regexHelper';

export const Signup = () => {

    const [signUpFormInput, setSignupFormInput] = useState({
        email : '',
        first_name: '',
        last_name: '',
        username: '',
        password: '',
        confirm_password: ''
    });

    const [invalidForm, setInvalidForm] = useState({
       email: false,
       first_name: false,
       last_name: false,
       username: false,
       password: false,
       password_mismatch: false,
       agreement: false,
       hasSubmitted: false
    });

    const [serverReply, setServerReply] = useState('');

    const history = useHistory();

    function handleFormOnChange(target){
        setSignupFormInput({...signUpFormInput, [target.name] : target.value});
        console.log(signUpFormInput);
    }

    function handleFormOnSubmit(event){

        event.preventDefault();


        // Check for forms input validation

        var isEmailInvalid = false;
        var isFirstNameInvalid = false;
        var isLastNameValid = false;
        var isUsernameInvalid = false;
        var isPasswordInvalid = false;
        var isPasswordMismatch = false;

        if(!emailValidation(signUpFormInput.email)){
            isEmailInvalid = true;
        }

        if(!nameValidation(signUpFormInput.first_name)){
            isFirstNameInvalid = true;
        }

        if(!nameValidation(signUpFormInput.last_name)){
            isLastNameValid = true;
        }

        if(!usernameValidation(signUpFormInput.username)){
            isUsernameInvalid = true;
        }

        if(!passwordValidation(signUpFormInput.password) || !passwordValidation(signUpFormInput.confirm_password)){
            isPasswordInvalid = true;
        }

        if(signUpFormInput.password !== signUpFormInput.confirm_password){
            isPasswordMismatch = true;
        }

        console.log(signUpFormInput.password);
        console.log(passwordValidation(signUpFormInput.password));

        if(isEmailInvalid || isFirstNameInvalid || isLastNameValid || isUsernameInvalid || isPasswordInvalid || isPasswordMismatch || !invalidForm.agreement){
            setInvalidForm({...invalidForm, email: isEmailInvalid, first_name: isFirstNameInvalid, last_name: isLastNameValid, username: isUsernameInvalid,
                 password: isPasswordInvalid, password_mismatch: isPasswordMismatch, hasSubmitted: true});
            return;
        }

        // Submit data to API

        const url = 'http://192.168.0.19:8080/api/auth/signup';

        axios.post(url, {
            displayUsername: signUpFormInput.username,
            firstName: signUpFormInput.first_name,
            lastName: signUpFormInput.last_name,
            email: signUpFormInput.email,
            password: signUpFormInput.password
        }).then(res => {
            console.log(res);
            history.push(`/account-verification?email=${signUpFormInput.email}`);
        }).catch(err => {
            console.log(err);
            setServerReply(err.response.data.message);
        });
        
    }

    return(
        <div className='signup-body'>
            
            <div className='signup-wrapper'>
                <div className='signup-container'>
                    <div className='signup-logo-container'>
                        <img src={LogoImg} alt='logo'/>
                    </div>
                    <p className='capital-text x-large-text'>Create an account</p>
                    <form className='sign-up-form' onSubmit={handleFormOnSubmit}>
                        
                        <br/>
                        <label className='capital-text'>Email</label><br/>
                        <EditText name='email' className='sign-up-edit-box' editStyle={invalidForm.email ? 'edit-text-error' : 'edit-text-light'} onChange={handleFormOnChange}/>

                        <br/><br/>

                        <div className='form-names'>
                            <div>
                                <label className='capital-text'>First Name </label><br/>
                                <EditText name='first_name' className='sign-up-edit-box' editStyle={invalidForm.first_name ? 'edit-text-error' : 'edit-text-light'} onChange={handleFormOnChange}/>
                            </div>
                            <div>
                                <label className='capital-text'>Last Name</label><br/>
                                <EditText name='last_name' className='sign-up-edit-box' editStyle={invalidForm.last_name ? 'edit-text-error' : 'edit-text-light'} onChange={handleFormOnChange}/>
                            </div>
                        </div>
                        
                        <br/>

                        <label className='capital-text'>Username</label><br/>
                        <label className='x-small-text'>(must contain either letters, or numbers with a length of 1-30)</label><br/>
                        <EditText name='username' className='sign-up-edit-box' editStyle={invalidForm.username ? 'edit-text-error' : 'edit-text-light'} onChange={handleFormOnChange}/>

                        <br/><br/>
                        
                        <label className='capital-text'>Password</label><br/>
                        <label className='x-small-text'>(must contain at least one letter, number, and a valid symbol (!@#$%^&+=) with a length of 8-16)</label><br/>
                        <EditText name='password' type='password' className='sign-up-edit-box' editStyle={invalidForm.password ? 'edit-text-error' : 'edit-text-light'} onChange={handleFormOnChange}/>

                        <br/><br/>

                        <label className='capital-text'>Confirm Password</label><br/>
                        <EditText name='confirm_password' type='password' className='sign-up-edit-box' editStyle={invalidForm.password ? 'edit-text-error' : 'edit-text-light'} onChange={handleFormOnChange}/>

                        <br/><br/>

                        <label className='small-text'>
                            <input type='checkbox' checked={invalidForm.agreement} onClick={() => setInvalidForm((prev) => ({...invalidForm, agreement:!prev.agreement}))}/> I have read and agree to Notes's <span className='small-blue-text' style={{cursor:'pointer'}}>Terms and Services</span> and <span className='small-blue-text' style={{cursor:'pointer'}}>Privacy Policy</span>
                        </label>

                        <br/><br/>

                        <input type='submit' value='Create Account' className='btn-large btn-round-blue submit-btn' style={{width:'100%'}} disabled={false}/>

                        <br/><br/>

                        <Button className='signup-signin' isLink={true} to='/'>Already have an account?</Button>

                        <br/><br/>

                    </form>

                    { serverReply !== '' ? <p className='signup-error small-error-text'>{serverReply}</p> : <></> }
                    { invalidForm.password_mismatch ? <p className='signup-error small-error-text'>Passwords do not match.</p> : <></> }
                    { !invalidForm.agreement && invalidForm.hasSubmitted ? <p className='signup-error small-error-text'>You must agree with our Terms and Services and Privacy Policy to continue.</p> : <></> }
                    { invalidForm.email || invalidForm.first_name || invalidForm.last_name || invalidForm.username || invalidForm.password ? <p className='signup-error small-error-text'>Please complete all fields correctly.</p> : <></> }

                </div>
            </div>

        </div>
    );

}