import React from 'react';
import '../styles/home.css';
import { Layout } from '../components/Layout';
import HeroImg from '../img/hero_image.svg';
import { Button } from '../components/Button';
import FreeImg from '../img/about_free.svg';
import MapImg from '../img/about_map.svg';
import DesignImg from '../img/about_design.svg';
import OrganisedImg from '../img/about_organised.svg';
import { TestimonyViewer } from '../components/TestimonyViewer';

const Home = () => {


    return(
        <Layout>
            <div className='hero-container'>
                <div className='hero-subject'>
                    <h1>Making notes has never been easier</h1>
                    <h2>Keep all your notes in one place and access them from any device, anywhere.</h2>
                    <Button btnSize='btn-large' btnStyle='btn-round-blue' isNavBtn={false} to='/noteboard'>Get Started</Button>
                </div>
                <div className='hero-image-container'>
                    <img src={HeroImg} alt='hero_image'/>
                </div>
            </div>
            <div id='about' className='about-container'>
                <h1>About</h1>
                <div className='grid-layout'>
                    <div className='grid-box'>
                        <div className='grid-image-container'>
                            <img src={FreeImg} alt='free'/>
                        </div>
                        <p className='grid-title'>It is Free!</p>
                        <p className='grid-detail'>Create notes and have access to unlimited storage.</p>
                    </div>
                    <div className='grid-box'>
                        <div className='grid-image-container'>
                            <img src={MapImg} alt='map'/>
                        </div>
                        <p className='grid-title'>Access from Anywhere!</p>
                        <p className='grid-detail'>Your notes stay up-to-date and can be accessed from any part of the world {'('}internet connection required{')'}.</p>
                    </div>
                    <div className='grid-box'>
                        <div className='grid-image-container'>
                            <img src={DesignImg} alt='design'/>
                        </div>
                        <p className='grid-title'>Powerful Editor!</p>
                        <p className='grid-detail'>Use one of the most powerful web text editor available. </p>
                    </div>
                    <div className='grid-box'>
                        <div className='grid-image-container'>
                            <img src={OrganisedImg} alt='organised'/>
                        </div>
                        <p className='grid-title'>Organised!</p>
                        <p className='grid-detail'>All your notes; organised and online 24/7.</p>
                    </div>
                </div>
            </div>
            <TestimonyViewer/>
        </Layout>
    );

}

export default Home;