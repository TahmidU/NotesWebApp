import React from 'react';
import '../styles/home.css';
import { Layout } from '../components/Layout';
import HeroImg from '../img/hero_image.svg';
import { Button } from '../components/Button';

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
        </Layout>
    );

}

export default Home;