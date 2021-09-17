import React, { useContext, useState } from 'react';
import { useHistory } from 'react-router';
import '../styles/theme.css';
import '../styles/signin.css';
import '../styles/text.css';
import '../styles/button.css';
import { Button } from '../components/Button';
import LogoImg from '../img/logo_without_text.svg';
import { EditText } from '../components/EditText';
import axios from 'axios';
import { ClipLoader } from 'react-spinners';
import { JWTContext } from '../App';

export const SignIn = () => {

    const [signInFormInput, setSignInFormInput] = useState({
        email: '',
        password: ''
    });

    const [userPrompt, setUserPrompt] = useState({
        message: '',
        loading: false
    });

    const history = useHistory();
    const { setJWTData } = useContext(JWTContext);

    const AuthFailedMsg = 'Email or password is incorrect';
    
    function handleFormOnChange(target){

        setSignInFormInput({...signInFormInput, [target.name] : target.value});
        console.log(signInFormInput);
    }

    function handleFormOnSubmit(event){
        
        event.preventDefault();

        const url = `http://192.168.0.19:8080/login`;

        setUserPrompt({...userPrompt, loading: true});

        axios.post(url, {
            email: signInFormInput.email,
            password: signInFormInput.password
        }).then(res => {
        
            if(res.data === ''){
                setUserPrompt({message:AuthFailedMsg, loading: false});
            }else{
                setJWTData(res.data);
                history.push(`/noteboard`);
            }
            
        }).catch(err => {
            setUserPrompt({...userPrompt, loading: false});
        });
        
    }

    return(
        <div className='signin-body'>
            <div className='signin-wrapper'>
                <div className='signin-container'>
                    <div className='signin-logo-container'>
                        <img src={LogoImg} alt='logo'/>
                    </div>
                    <p className='capital-text x-large-text'>Welcome back!</p>
                    <form className='sign-in-form' onSubmit={handleFormOnSubmit}>

                        <br/>

                        <label className='capital-text'>Email</label>
                        <EditText name='email' className='sign-in-edit-box' editStyle={userPrompt.message === '' ? 'edit-text-light' : 'edit-text-error' } onChange={handleFormOnChange} />

                        <br/><br/>

                        <label className='capital-text'>Password</label>
                        <EditText name='password' type='password' className='sign-in-edit-box' editStyle={userPrompt.message === '' ? 'edit-text-light' : 'edit-text-error' } onChange={handleFormOnChange} />

                        <br/><br/>

                        <input type='submit' value='Sign In' className='btn-large btn-round-blue submit-btn' style={{width:'100%'}} disabled={false}/>

                        <br/><br/>

                        <p className='small-text'>Need an account? <Button className='signup-signin' isLink={true} to='/sign-up'>Register</Button></p>
                        <Button className='signup-signin' isLink={true} to='/'>Trouble signing in?</Button>

                        <br/><br/>
                    </form>

                    { userPrompt.message !== '' ? <p className='signin-error small-error-text'>{userPrompt.message}</p> : <></> }

                </div>
            </div>

            { userPrompt.loading ? <div className='signin-loading'><ClipLoader size={150} color='#256CE1' loading={userPrompt.loading}/></div> : <></> }

        </div>
    );


}