import React from 'react';
import { Layout } from '../components/Layout';
import { NoteCard } from '../components/NoteCard';

export const Noteboard = () => {

    return(
        <Layout>
            <div className='search-container'>

            </div>
            <div className='notes-list-container' style={{display:'flex', justifyContent:'center'}}>
                <ul style={{width: '80%', listStyleType:'none'}}>
                    <NoteCard note={{id:1, title:'tT', content:'ct'}}/>
                </ul>
                
            </div>
        </Layout>
    );

}