import React, {Fragment} from 'react';

import {Box, Paper, Typography} from '@material-ui/core';
import {Euro, Home, Storefront} from '@material-ui/icons';
import DefaultImage from './default-image.jpg'

function House({data}) {
    return (
        <Fragment>
            <img style={{width: "100%"}}
                 src={data.imgUrl ? data.imgUrl : DefaultImage}/>
            <Paper style={{padding: "1rem"}}>
                <Typography variant="subtitle1">{data.title}</Typography>
                <Box display="flex" mt="0.3rem">
                    <Storefront/>
                    <Typography variant="body1">{data.provider}</Typography>
                </Box>
                <Box display="flex" mt="0.3rem">
                    <Home/>
                    <Typography variant="body1">{data.address}</Typography>
                </Box>
                {data.price &&
                <Box display="flex" mt="0.3rem">
                    <Euro/>
                    <Typography variant="body1">{data.price}</Typography>
                </Box>
                }
            </Paper>
        </Fragment>
    );
}

export default House;
