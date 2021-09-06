import React, { useState } from 'react';
import { useHistory } from 'react-router';
import '../styles/theme.css';
import '../styles/text.css';
import '../styles/button.css';
import '../styles/account_verification.css';
import LogoImg from '../img/logo_without_text.svg';
import { EditText } from '../components/EditText';
import axios from 'axios';
import { useURLQuery } from '../util/URLQuery';
import { ClipLoader } from 'react-spinners';

export const AccountVerification = () => {

    let urlQuery = useURLQuery();
    const history = useHistory();

    const [verifyCode, setVerifyCode] = useState();
    const [serverReply, setServerReply] = useState({
        message: '',
        loading: false
    });

    function getVerifyCode(target){

        setVerifyCode(target.value);
    }

    function handleFormOnSubmit(event){

        event.preventDefault();

        const url = `http://192.168.0.19:8080/api/auth/verify?email=${urlQuery.get('email')}&token=${verifyCode}`;

        setServerReply({...serverReply, loading: true});
        axios.post(url).then(res => {
            console.log(res);
            setServerReply({...serverReply, loading: false});
            history.push(`/`);
        }).catch(err => {
            console.log(err);
            setServerReply({message: err.response.data.message, loading: false});
        });
    }

    return(
        <div className='verify-body'>
           <div className='verify-wrapper'>
               <div className='verify-container'>
                   <div className='verify-logo-container'>
                        <img src={LogoImg} alt='logo'/>
                   </div>
                   <p className='capital-text x-large-text'>Verify Account</p>
                   <form className='verify-form' onSubmit={handleFormOnSubmit}>

                       <div style={{margin:'0.625rem 0'}}></div>
                       <p className='medium-text' style={{textAlign:'center'}}>You should have received an email from us containing a token. Give it a few minutes or check your junk mail if has not arrived yet.</p>
                       <EditText name='verification_code' className='verify-edit-box' editStyle={'edit-text-light'} maxLength={5} textPattern='\d*' onChange={getVerifyCode}/>
                       <div style={{margin:'0.625rem 0'}}></div>

                       { serverReply.message !== '' ? <p style={{display:'block', margin:'0.625rem 0'}} className='small-error-text'>{serverReply.message}</p> : <></> }

                       <div style={{margin:'0.625rem 0'}}></div>
                       <input type='submit' value='Enter Code' className='btn-large btn-round-blue submit-btn' style={{width:'100%'}}/>

                   </form>

               </div>
           </div>

            {serverReply.loading ? <div className='verify-loading'><ClipLoader size={150} color='#256CE1' loading={serverReply.loading}/></div> : <></>}

        </div>
    );

}