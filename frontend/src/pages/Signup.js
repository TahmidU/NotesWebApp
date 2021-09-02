import React, { useState } from 'react';
import '../styles/theme.css';
import '../styles/signup.css';
import '../styles/text.css';
import '../styles/button.css';
import LogoImg from '../img/logo_without_text.svg';
import { Button } from '../components/Button';
import axios from 'axios';
import { EditText } from '../components/EditText';

export const Signup = () => {



    return(
        <div className='signup-body'>
            
            <div className='signup-wrapper'>
                <div className='signup-container'>
                    <div className='signup-logo-container'>
                        <img src={LogoImg} alt='logo'/>
                    </div>
                    <p className='capital-text x-large-text'>Create an account</p>
                    <form className='sign-up-form'>
                        
                        <br/>
                        <label className='capital-text'>Email</label><br/>
                        <EditText className='sign-up-edit-box' editStyle='edit-text-light' limit={-1}/>

                        <br/><br/>

                        <label className='capital-text'>Username</label><br/>
                        <label className='x-small-text'>(must contain letters, and numbers with a length of 6-20)</label><br/>
                        <EditText className='sign-up-edit-box' editStyle='edit-text-light' limit={-1}/>

                        <br/><br/>
                        
                        <label className='capital-text'>Password</label><br/>
                        <label className='x-small-text'>(must contain at least one letter, number, and a valid symbol (!@#$%^&+=) with a length of 8-16)</label><br/>
                        <EditText className='sign-up-edit-box' editStyle='edit-text-light' limit={-1}/>

                        <br/><br/>

                        <label className='capital-text'>Confirm Password</label><br/>
                        <EditText className='sign-up-edit-box' editStyle='edit-text-light' limit={-1}/>

                        <br/>

                        <label className='small-text'><br/>
                            <input type='checkbox'/> I have read and agree to Notes's <span className='small-blue-text' style={{cursor:'pointer'}}>Terms and Services</span> and <span className='small-blue-text' style={{cursor:'pointer'}}>Privacy Policy</span>
                        </label>

                        <br/><br/>

                        <input type='submit' className='btn-large btn-round-blue submit-btn' style={{width:'100%'}} disabled={false}/>

                        <br/><br/>

                        <Button className='signup-signin' isLink={true} to='/'>Already have an account?</Button>

                        <br/><br/>

                    </form>
                </div>
            </div>

        </div>
    );

}