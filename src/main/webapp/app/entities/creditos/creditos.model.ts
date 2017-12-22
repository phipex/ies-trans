import { BaseEntity } from './../../shared';

export class Creditos implements BaseEntity {
    constructor(
        public id?: number,
        public coinIn?: number,
        public coinOut?: number,
        public handpay?: number,
        public jackpot?: number,
        public bill?: number,
    ) {
    }
}
